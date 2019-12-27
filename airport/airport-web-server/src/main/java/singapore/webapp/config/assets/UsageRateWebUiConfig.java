package singapore.webapp.config.assets;

import static java.lang.String.format;
import static singapore.common.StandardScrollingConfigs.standardStandaloneScrollingConfig;
import static ua.com.fielden.platform.web.PrefDim.mkDim;

import java.util.Optional;

import com.google.inject.Injector;

import singapore.assets.Asset;
import singapore.assets.UsageRate;
import singapore.common.LayoutComposer;
import singapore.common.StandardActions;
import singapore.main.menu.assets.MiUsageRate;
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
 * {@link UsageRate} Web UI configuration.
 *
 * @author Developers
 *
 */
public class UsageRateWebUiConfig {

    public final EntityCentre<UsageRate> centre;
    public final EntityMaster<UsageRate> master;

    public static UsageRateWebUiConfig register(final Injector injector, final IWebUiBuilder builder) {
        return new UsageRateWebUiConfig(injector, builder);
    }

    private UsageRateWebUiConfig(final Injector injector, final IWebUiBuilder builder) {
        centre = createCentre(injector, builder);
        builder.register(centre);
        master = createMaster(injector);
        builder.register(master);
    }

    /**
     * Creates entity centre for {@link UsageRate}.
     *
     * @param injector
     * @return created entity centre
     */
    private EntityCentre<UsageRate> createCentre(final Injector injector, final IWebUiBuilder builder) {
        final String layout = LayoutComposer.mkGridForCentre(1, 3);

        final EntityActionConfig standardNewAction = StandardActions.NEW_ACTION.mkAction(UsageRate.class);
        final EntityActionConfig standardDeleteAction = StandardActions.DELETE_ACTION.mkAction(UsageRate.class);
        final EntityActionConfig standardExportAction = StandardActions.EXPORT_ACTION.mkAction(UsageRate.class);
        final EntityActionConfig standardEditAction = StandardActions.EDIT_ACTION.mkAction(UsageRate.class);
        final EntityActionConfig standardSortAction = CentreConfigActions.CUSTOMISE_COLUMNS_ACTION.mkAction();
        builder.registerOpenMasterAction(UsageRate.class, standardEditAction);

        final EntityCentreConfig<UsageRate> ecc = EntityCentreBuilder.centreFor(UsageRate.class)
                //.runAutomatically()
                .addFrontAction(standardNewAction)
                .addTopAction(standardNewAction).also()
                .addTopAction(standardDeleteAction).also()
                .addTopAction(standardSortAction).also()
                .addTopAction(standardExportAction)
                .addCrit("key").asMulti().autocompleter(Asset.class).also()
                .addCrit("planDate").asRange().date().also()
                .addCrit("usagerate").asMulti().text()
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withScrollingConfig(standardStandaloneScrollingConfig(0))
                .addProp("key").order(1).asc().minWidth(100)
                    .withSummary("total_count_", "COUNT(SELF)", format("Count:The total number of matching %ss.", Asset.ENTITY_TITLE))
                    .withAction(standardEditAction).also()
                .addProp("planDate").minWidth(150).also()
                .addProp("usagerate").minWidth(100)
                //.addProp("prop").minWidth(100).withActionSupplier(builder.getOpenMasterAction(Entity.class)).also()
                .addPrimaryAction(standardEditAction)
                .build();

        return new EntityCentre<>(MiUsageRate.class, MiUsageRate.class.getSimpleName(), ecc, injector, null);
    }

    /**
     * Creates entity master for {@link UsageRate}.
     *
     * @param injector
     * @return created entity master
     */
    private EntityMaster<UsageRate> createMaster(final Injector injector) {
        final String layout = LayoutComposer.mkGridForMasterFitWidth(1, 3);

        final IMaster<UsageRate> masterConfig = new SimpleMasterBuilder<UsageRate>().forEntity(UsageRate.class)
                .addProp("key").asAutocompleter().also()
                .addProp("planDate").asDatePicker().also()
                .addProp("usagerate").asMultilineText().also()
                .addAction(MasterActions.REFRESH).shortDesc("Cancel").longDesc("Cancel action")
                .addAction(MasterActions.SAVE)
                .setActionBarLayoutFor(Device.DESKTOP, Optional.empty(), LayoutComposer.mkActionLayoutForMaster())
                .setLayoutFor(Device.DESKTOP, Optional.empty(), layout)
                .setLayoutFor(Device.TABLET, Optional.empty(), layout)
                .setLayoutFor(Device.MOBILE, Optional.empty(), layout)
                .withDimensions(mkDim(LayoutComposer.SIMPLE_ONE_COLUMN_MASTER_DIM_WIDTH, 480, Unit.PX))
                .done();

        return new EntityMaster<>(UsageRate.class, masterConfig, injector);
    }
}