package singapore.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link AssetsLocator}.
 *
 * @author Developers
 *
 */
public interface IAssetsLocator extends IEntityDao<AssetsLocator> {

    static final IFetchProvider<AssetsLocator> FETCH_PROVIDER = EntityUtils.fetch(AssetsLocator.class).with("assets");

}
