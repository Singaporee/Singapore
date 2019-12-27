package singapore.webapp.config.users;

import static java.lang.String.format;
import static singapore.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import singapore.users.AssetOperator;
import singapore.assets.Asset;
import singapore.assets.AssetFinDet;
import singapore.common.LayoutComposer;
import singapore.common.StandardActions;

import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import singapore.main.menu.users.MiAssetOperator;
import singapore.organizational.BusinessUnit;
import singapore.organizational.Organisation;
import singapore.organizational.Role;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
/**
 * {@link AssetOperator} Web UI configuration.
 *
 * @author Developers
 *
 */

public class AssetOperatorWebUiConfig {

   public final EntityCentre<AssetOperator> centre;
   public final EntityMaster<AssetOperator> master;

   public static AssetOperatorWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
       return new AssetOperatorWebUiConfig(injector, builder);
   }

   private AssetOperatorWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
       centre = createCentre(injector, builder);
       builder.register(centre);
       master = createMaster(injector);
       builder.register(master);
   }

   /**
    * Creates entity centre for {@link AssetOperator}.
    *
    * @param injector
    * @return created entity centre
    */
   private EntityCentre<AssetOperator> createCentre(final Injector injector, final IWebUiBuilder builder) {
       final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

       final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetOperator.class);
       final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetOperator.class);
       final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetOperator.class);
       final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetOperator.class);
       final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
       builder.registerOpenMasterAction(AssetOperator.class, standardEditAction);

       final EntityCentreConfig<AssetOperator> ecc = EntityCentreBuilder.centreFor(AssetOperator.class)
               .addFrontAction(standardNewAction)
               .addTopAction(standardNewAction).also()
               .addTopAction(standardDeleteAction).also()
               .addTopAction(standardSortAction).also()
               .addTopAction(standardExportAction)
               .addCrit("key").asMulti().autocompleter(Asset.class).also()
               .addCrit("startDate").asRange().date().also()
               .addCrit("role").asMulti().autocompleter(Role.class).also()
               .addCrit("bu").asMulti().autocompleter(BusinessUnit.class).also()
               .addCrit("org").asMulti().autocompleter(Organisation.class)
               .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
               .setLayoutFor(Device.TABLET, Optional.empty(), layout)
               .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
               .withScrollingConfig(standardStandaloneScrollingConfig(0))
               .addProp("key").order(1).asc().width(100)
                   .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetFinDet.ENTITY_TITLE))
                   .withActionSupplier(builder.getOpenMasterAction(Asset.class)).also()
               .addProp("startDate").width(150).also()
               .addProp("role").minWidth(100).also()
               .addProp("bu").minWidth(100).also()
               .addProp("org").minWidth(100)
               //TODO: add here OpenMasterAction to all of three once the master for them exists
               .addPrimaryAction(standardEditAction)
               .build();

       return new EntityCentre<>(MiAssetOperator.class, MiAssetOperator.class.getSimpleName(), ecc, injector, null);
   }

   /**
    * Creates entity master for {@link AssetOperator}.
    *
    * @param injector
    * @return created entity master
    */
   private EntityMaster<AssetOperator> createMaster(final Injector injector) {
       final String layout = LayoutComposer.mkGridForMasterFitWidth(5, 1);

       final IMaster<AssetOperator> masterConfig = new SimpleMasterBuilder<AssetOperator>().forEntity(AssetOperator.class)
               .addProp("key").asAutocompleter().also()
               .addProp("startDate").asDatePicker().also()
               .addProp("role").asAutocompleter().also()
               .addProp("bu").asAutocompleter().also()
               .addProp("org").asAutocompleter().also()
               .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
               .addAction(MasterActions.SAVE)
               .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
               .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
               .setLayoutFor(Device.TABLET, Optional.empty(), layout)
               .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
               .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
               .done();

       return new EntityMaster<>(AssetOperator.class, masterConfig, injector);
   }
} 