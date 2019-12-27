package singapore.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link UsageRate}.
 *
 * @author Developers
 *
 */
public interface IUsageRate extends IEntityDao<UsageRate> {

    static final IFetchProvider<UsageRate> FETCH_PROVIDER = EntityUtils.fetch(UsageRate.class)
            .with("key","planDate", "usagerate");

}
