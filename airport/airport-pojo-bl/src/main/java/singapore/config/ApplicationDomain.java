package singapore.config;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import singapore.personnel.Person;
import ua.com.fielden.platform.basic.config.IApplicationDomainProvider;
import ua.com.fielden.platform.domain.PlatformDomainTypes;
import ua.com.fielden.platform.entity.AbstractEntity;
import singapore.asset.tablecodes.AssetClass;
import singapore.asset.tablecodes.AssetType;
import singapore.assets.Asset;
import singapore.services.Servicestatus;
import singapore.services.ConditionRating;
import singapore.assets.AssetFinDet;
import singapore.projects.Project;
import singapore.organizational.Role;
import singapore.organizational.Organisation;
import singapore.organizational.BusinessUnit;
import singapore.assets.AssetTypeOwnership;
import singapore.users.AssetOperator;
import singapore.users.AssetManager;

/**
 * A class to register domain entities.
 * 
 * @author TG Team
 * 
 */
public class ApplicationDomain implements IApplicationDomainProvider {
    private static final Set<Class<? extends AbstractEntity<?>>> entityTypes = new LinkedHashSet<>();
    private static final Set<Class<? extends AbstractEntity<?>>> domainTypes = new LinkedHashSet<>();

    static {
        entityTypes.addAll(PlatformDomainTypes.types);
        add(Person.class);
        add(AssetClass.class);
        add(AssetType.class);
        add(Asset.class);
        add(Servicestatus.class);
        add(ConditionRating.class);
        add(AssetFinDet.class);
        add(Project.class);
        add(Role.class);
        add(Organisation.class);
        add(BusinessUnit.class);
        add(AssetTypeOwnership.class);
        add(AssetOperator.class);
        add(AssetManager.class);
    }

    private static void add(final Class<? extends AbstractEntity<?>> domainType) {
        entityTypes.add(domainType);
        domainTypes.add(domainType);
    }

    @Override
    public List<Class<? extends AbstractEntity<?>>> entityTypes() {
        return Collections.unmodifiableList(entityTypes.stream().collect(Collectors.toList()));
    }

    public List<Class<? extends AbstractEntity<?>>> domainTypes() {
        return Collections.unmodifiableList(domainTypes.stream().collect(Collectors.toList()));
    }
}
