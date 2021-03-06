package singapore.webapp.config.assets;

import static java.lang.String.format;
import static singapore.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import singapore.asset.tablecodes.AssetType;
import singapore.assets.Asset;
import singapore.common.LayoutComposer;
import singapore.common.StandardActions;
import singapore.main.menu.assets.MiAsset;
import singapore.services.ConditionRating;
import singapore.services.Servicestatus;
import ua.com.fielden.platform.web.PrefDim.Unit;
import ua.com.fielden.platform.web.action.CentreConfigurationWebUiConfig.CentreConfigActions;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.centre.EntityCentre;
import ua.com.fielden.platform.web.centre.api.EntityCentreConfig;
import ua.com.fielden.platform.web.centre.api.actions.EntityActionConfig;
import ua.com.fielden.platform.web.centre.api.impl.EntityCentreBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.view.master.EntityMaster;
import ua.com.fielden.platform.web.view.master.api.IMaster;
import ua.com.fielden.platform.web.view.master.api.actions.MasterActions;
import ua.com.fielden.platform.web.view.master.api.impl.SimpleMasterBuilder;
/**
 * {@link Asset} Web UI configuration.
 *
 * @author Developers
 *
 */
public class AssetWebUiConfig {

    public final EntityCentre<Asset> centre;
    public final EntityMaster<Asset> master;

    public static AssetWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new AssetWebUiConfig(injector, builder);
    }

    private AssetWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link Asset}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<Asset> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(5, 4);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(Asset.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(Asset.class, standardEditAction);

        final EntityCentreConfig<Asset> ecc = EntityCentreBuilder.centreFor(Asset.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("this").asMulti().autocompleter(Asset.class).also()
                .addCrit("desc").asMulti().text().also()
                .addCrit("assetType").asMulti().autocompleter(AssetType.class).also()
                .addCrit("conditionRating").asMulti().autocompleter(ConditionRating.class).also()
                .addCrit("servicestatus").asMulti().autocompleter(Servicestatus.class).also()
                .addCrit("active").asMulti().bool().also()
                .addCrit("regulatory").asMulti().bool().also()
                .addCrit("keyservice").asMulti().bool()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("this").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Asset.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("desc").minWidth(100).also()
                .addProp("assetType").minWidth(100).also()
                .addProp("conditionRating").minWidth(100).also()
                .addProp("servicestatus").minWidth(100).also()
                .addProp("active").width(100).also()
                .addProp("regulatory").width(100).also()
                .addProp("keyservice").width(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiAsset.class, MiAsset.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link Asset}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<Asset> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(8, 1);

        final IMaster<Asset> masterConfig = new SimpleMasterBuilder<Asset>().forEntity(Asset.class)
                .addProp("number").asSinglelineText().also()
                .addProp("desc").asMultilineText().also()
                .addProp("assetType").asAutocompleter().also()
                .addProp("conditionRating").asAutocompleter().also()
                .addProp("servicestatus").asAutocompleter().also()
                .addProp("active").asCheckbox().also()
                .addProp("regulatory").asCheckbox().also()
                .addProp("keyservice").asCheckbox().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(Asset.class, masterConfig, injector);
    }
} 