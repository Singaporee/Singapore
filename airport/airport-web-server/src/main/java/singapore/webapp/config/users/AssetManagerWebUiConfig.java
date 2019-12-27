package singapore.webapp.config.users;

import static java.lang.String.format;
import static singapore.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;

import java.util.Optional;

import com.google.inject.Injector;

import singapore.users.AssetManager;
import singapore.users.AssetManager;
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
import singapore.main.menu.users.MiAssetManager;
import singapore.main.menu.users.MiAssetManager;
import singapore.organizational.BusinessUnit;
import singapore.organizational.Organisation;
import singapore.organizational.Role;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import static ua.com.fielden.platform.web.PrefDim.mkDim;
import ua.com.fielden.platform.web.PrefDim.Unit;
/**
 * {@link AssetManager} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetManagerWebUiConfig {

    public final EntityCentre<AssetManager> centre;
    public final EntityMaster<AssetManager> master;

    public static AssetManagerWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetManagerWebUiConfig(injector, builder);
    }

    private AssetManagerWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link AssetManager}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<AssetManager> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkVarGridForCentre(2, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(AssetManager.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(AssetManager.class, standardEditAction);

        final EntityCentreConfig<AssetManager> ecc = EntityCentreBuilder.centreFor(AssetManager.class)
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
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", AssetManager.ENTITY_TITLE))
                    .withActionSupplier(builder.getOpenMasterAction(Asset.class)).also()
                .addProp("startDate").width(150).also()
                .addProp("role").minWidth(100).also()
                .addProp("bu").minWidth(100).also()
                .addProp("org").minWidth(100)
                //TODO: add here OpenMasterAction to all of three once the master for them exists
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAssetManager.class, MiAssetManager.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link AssetManager}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<AssetManager> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(5, 1);

        final IMaster<AssetManager> masterConfig = new SimpleMasterBuilder<AssetManager>().forEntity(AssetManager.class)
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

        return new EntityMaster<>(AssetManager.class, masterConfig, injector);
    }
 } 