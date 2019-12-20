package singapore.services;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IServicestatus}.
 *
 * @author Developers
 *
 */
@EntityType(Servicestatus.class)
public class ServicestatusDao extends CommonEntityDao<Servicestatus> implements IServicestatus {

    @Inject
    public ServicestatusDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<Servicestatus> createFetchProvider() {
            return FETCH_PROVIDER;
    }
}