package singapore.assets;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.utils.EntityUtils;
import ua.com.fielden.platform.dao.IEntityDao;

/**
 * Companion object for entity {@link Asset}.
 *
 * @author Developers
 *
 */
public interface IAsset extends IEntityDao<Asset> {

    static final IFetchProvider<Asset> FETCH_PROVIDER = EntityUtils.fetch(Asset.class).with(
        // TODO: uncomment the following line and specify the properties, which are required for the UI. Then remove the line after.
        // "key", "desc");
        "Please specify the properties, which are required for the UI");

}
