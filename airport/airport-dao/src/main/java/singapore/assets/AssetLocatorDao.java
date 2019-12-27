package singapore.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetLocator}.
 *
 * @author Developers
 *
 */
@EntityType(AssetLocator.class)
public class AssetLocatorDao extends CommonEntityDao<AssetLocator> implements IAssetLocator {

    @Inject
    public AssetLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
