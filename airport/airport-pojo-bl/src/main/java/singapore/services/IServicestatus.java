package singapore.services;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Servicestatus}.
 *
 * @author Developers
 *
 */
public interface IServicestatus extends IEntityDao<Servicestatus> {

    static final IFetchProvider<Servicestatus> FETCH_PROVIDER = EntityUtils.fetch(Servicestatus.class)
            .with("name", "desc", "asset" ,"startDate");

}
