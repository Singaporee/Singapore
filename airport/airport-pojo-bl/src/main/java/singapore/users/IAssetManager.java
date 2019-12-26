package singapore.users;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetManager}.
 *
 * @author Developers
 *
 */
public interface IAssetManager extends IEntityDao<AssetManager> {

    static final IFetchProvider<AssetManager> FETCH_PROVIDER = EntityUtils.fetch(AssetManager.class)
            .with("key","startDate", "role", "bu" ,"org");
}
