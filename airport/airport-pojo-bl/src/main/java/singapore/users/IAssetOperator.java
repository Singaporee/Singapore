package singapore.users;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetOperator}.
 *
 * @author Developers
 *
 */
public interface IAssetOperator extends IEntityDao<AssetOperator> {

    static final IFetchProvider<AssetOperator> FETCH_PROVIDER = EntityUtils.fetch(AssetOperator.class)
            .with("key","startDate", "role", "bu" ,"org");

}
