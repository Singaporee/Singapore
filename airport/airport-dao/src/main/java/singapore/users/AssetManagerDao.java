package singapore.users;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetManager}.
 *
 * @author Developers
 *
 */
@EntityType(AssetManager.class)
public class AssetManagerDao extends CommonEntityDao<AssetManager> implements IAssetManager {

    @Inject
    public AssetManagerDao(final IFilter filter) {
        super(filter);
    }

    @Override
    public AssetManager new_() {
        final AssetManager operation = super.new_();
        operation.getProperty("role").setRequired(true);
        operation.getProperty("bu").setRequired(true);
        operation.getProperty("org").setRequired(true);
        return operation;
    }
    
    @Override
    protected IFetchProvider<AssetManager> createFetchProvider() {
            return FETCH_PROVIDER;
      
    }
}
