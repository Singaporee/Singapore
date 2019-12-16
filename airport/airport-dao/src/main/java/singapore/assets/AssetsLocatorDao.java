package singapore.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetsLocator}.
 *
 * @author Developers
 *
 */
@EntityType(AssetsLocator.class)
public class AssetsLocatorDao extends CommonEntityDao<AssetsLocator> implements IAssetsLocator {

    @Inject
    public AssetsLocatorDao(final IFilter filter) {
        super(filter);
    }

    @Override
    protected IFetchProvider<AssetsLocator> createFetchProvider() {
        return FETCH_PROVIDER;
    }

}
