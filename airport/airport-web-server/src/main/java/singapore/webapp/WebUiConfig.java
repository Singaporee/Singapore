package singapore.webapp;

import org.apache.commons.lang.StringUtils;
import static java.lang.String.format;
import singapore.asset.tablecodes.AssetClass;
import singapore.asset.tablecodes.AssetType;
import singapore.assets.Asset;
import singapore.assets.AssetFinDet;
import singapore.assets.AssetTypeOwnership;
import singapore.config.personnel.PersonWebUiConfig;
import singapore.projects.Project;
import singapore.services.ConditionRating;
import singapore.services.Servicestatus;
import singapore.users.AssetOperator;
import singapore.webapp.config.asset.tablecodes.AssetClassWebUiConfig;
import singapore.webapp.config.asset.tablecodes.AssetTypeWebUiConfig;
import singapore.webapp.config.assets.AssetFinDetWebUiConfig;
import singapore.webapp.config.assets.AssetTypeOwnershipWebUiConfig;
import singapore.webapp.config.assets.AssetWebUiConfig;
import singapore.webapp.config.projects.ProjectWebUiConfig;
import singapore.webapp.config.services.ConditionRatingWebUiConfig;
import singapore.webapp.config.services.ServicestatusWebUiConfig;
import singapore.webapp.config.users.AssetOperatorWebUiConfig;
import ua.com.fielden.platform.basic.config.Workflows;
import ua.com.fielden.platform.web.app.config.IWebUiBuilder;
import ua.com.fielden.platform.web.interfaces.ILayout.Device;
import ua.com.fielden.platform.web.resources.webui.AbstractWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserRoleWebUiConfig;
import ua.com.fielden.platform.web.resources.webui.UserWebUiConfig;

/**
 * App-specific {@link IWebApp} implementation.
 *
 * @author Generated
 *
 */
public class WebUiConfig extends AbstractWebUiConfig {

    private final String domainName;
    private final String path;
    private final int port;

    public WebUiConfig(final String domainName, final int port, final Workflows workflow, final String path) {
        super("Singapore Airport Asset Management (development)", workflow, new String[] { "singapore/" });
        if (StringUtils.isEmpty(domainName) || StringUtils.isEmpty(path)) {
            throw new IllegalArgumentException("Both the domain name and application binding path should be specified.");
        }
        this.domainName = domainName;
        this.port = port;
        this.path = path;
    }


    @Override
    public String getDomainName() {
        return domainName;
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public int getPort() {
        return port;
    }

    /**
     * Configures the {@link WebUiConfig} with custom centres and masters.
     */
    @Override
    public void initConfiguration() {
        super.initConfiguration();

        final IWebUiBuilder builder = configApp();
        builder.setDateFormat("DD/MM/YYYY").setTimeFormat("HH:mm").setTimeWithMillisFormat("HH:mm:ss")
        // Minimum tablet width defines the boundary below which mobile layout takes place.
        // For example for Xiaomi Redmi 4x has official screen size of 1280x640,
        // still its viewport sizes is twice lesser: 640 in landscape orientation and 360 in portrait orientation.
        // When calculating reasonable transition tablet->mobile we need to consider "real" viewport sizes instead of physical pixel sizes (http://viewportsizes.com).
        .setMinTabletWidth(600);

        // Personnel
        final PersonWebUiConfig personWebUiConfig = PersonWebUiConfig.register(injector(), builder);
        final UserWebUiConfig userWebUiConfig = new UserWebUiConfig(injector());
        final UserRoleWebUiConfig userRoleWebUiConfig = new UserRoleWebUiConfig(injector());
        
        //UI for table codes for assets
        final AssetClassWebUiConfig assetClassWebUiConfig = AssetClassWebUiConfig.register(injector(), builder);
        final AssetTypeWebUiConfig assetTypeWebUiConfig = AssetTypeWebUiConfig.register(injector(), builder);
        final ServicestatusWebUiConfig servicestatusWebUiConfig = ServicestatusWebUiConfig.register(injector(), builder);
        final ConditionRatingWebUiConfig conditionRatingWebUiConfig = ConditionRatingWebUiConfig.register(injector(), builder);
        
        //Asset
        final AssetWebUiConfig assetWebUiConfig = AssetWebUiConfig.register(injector(), builder);
        final AssetFinDetWebUiConfig assetFinDetWebUiConfig = AssetFinDetWebUiConfig.register(injector(), builder);
        final AssetTypeOwnershipWebUiConfig assetTypeOwnershipWebUiConfig = AssetTypeOwnershipWebUiConfig.register(injector(), builder);
        final AssetOperatorWebUiConfig assetOperatorWebUiConfig = AssetOperatorWebUiConfig.register(injector(), builder);
        //Project
        final ProjectWebUiConfig projectWebUiConfig = ProjectWebUiConfig.register(injector(), builder);
        
        // Configure application web resources such as masters and centres
        configApp()
        .addMaster(userWebUiConfig.master)
        .addMaster(userWebUiConfig.rolesUpdater)
        .addMaster(userRoleWebUiConfig.master)
        .addMaster(userRoleWebUiConfig.tokensUpdater)
        // user/personnel module
        .addCentre(userWebUiConfig.centre)
        .addCentre(userRoleWebUiConfig.centre);

        // Configure application menu
        configDesktopMainMenu().
        addModule("Asset Acquisition").
            description("Asset acquisition module").
            icon("mainMenu:equipment").
            detailIcon("mainMenu:equipment").
            bgColor("#FFE680").
            captionBgColor("#FFD42A").menu()
            .addMenuItem(Asset.ENTITY_TITLE).description(format("%s Centre", Asset.ENTITY_TITLE)).centre(assetWebUiConfig.centre).done()
            .addMenuItem(AssetFinDet.ENTITY_TITLE).description(format("%s Centre", AssetFinDet.ENTITY_TITLE)).centre(assetFinDetWebUiConfig.centre).done()
            .addMenuItem(Project.ENTITY_TITLE).description(format("%s Centre", Project.ENTITY_TITLE)).centre(projectWebUiConfig.centre).done()
                .done().done().
            addModule("Users / Personnel").
                description("Provides functionality for managing application security and personnel data.").
                icon("mainMenu:help").
                detailIcon("anotherMainMenu:about").
                bgColor("#FFE680").
                captionBgColor("#FFD42A").menu()
                .addMenuItem("Personnel").description("Personnel related data")
                    .addMenuItem("Personnel").description("Personnel Centre").centre(personWebUiConfig.centre).done()
                .done()
                .addMenuItem("Users").description("Users related data")
                    .addMenuItem("Users").description("User centre").centre(userWebUiConfig.centre).done()
                    .addMenuItem("User Roles").description("User roles centre").centre(userRoleWebUiConfig.centre).done()
                    .addMenuItem(AssetOperator.ENTITY_TITLE).description(String.format("%s Centre", AssetOperator.ENTITY_TITLE))
                    .centre(assetOperatorWebUiConfig.centre).done()
                .done().
            done().done().
            addModule("Table Codes").
            description("Table Codes Description").
            icon("mainMenu:tablecodes").
            detailIcon("mainMenu:tablecodes").
            bgColor("#FFE680").
            captionBgColor("#FFD42A").menu()
                .addMenuItem("Asset Table Codes").description("Various master data for assets.")
                .addMenuItem(AssetClass.ENTITY_TITLE).description(String.format("%s Centre", AssetClass.ENTITY_TITLE))
                .centre(assetClassWebUiConfig.centre).done()
                .addMenuItem(AssetType.ENTITY_TITLE).description(String.format("%s Centre", AssetType.ENTITY_TITLE))
                .centre(assetTypeWebUiConfig.centre).done()
                .addMenuItem(Servicestatus.ENTITY_TITLE).description(String.format("%s Centre", Servicestatus.ENTITY_TITLE))
                .centre(servicestatusWebUiConfig.centre).done()
                .addMenuItem(ConditionRating.ENTITY_TITLE).description(String.format("%s Centre", ConditionRating.ENTITY_TITLE))
                .centre(conditionRatingWebUiConfig.centre).done()
                .addMenuItem(AssetTypeOwnership.ENTITY_TITLE).description(String.format("%s Centre", AssetTypeOwnership.ENTITY_TITLE))
                .centre(assetTypeOwnershipWebUiConfig.centre).done()
            .done().
        done().done()
    .setLayoutFor(Device.DESKTOP, null, "[[[{\"rowspan\":2}], []], [[]]]")
    .setLayoutFor(Device.TABLET, null,  "[[[{\"rowspan\":2}], []], [[]]]")
    .setLayoutFor(Device.MOBILE, null, "[[[]],[[]], [[]]]")
    .minCellWidth(100).minCellHeight(148).done();
    }
    }
