package singapore.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetTypeOwnership}.
 *
 * @author Developers
 *
 */
@EntityType(AssetTypeOwnership.class)
public class AssetTypeOwnershipDao extends CommonEntityDao<AssetTypeOwnership> implements IAssetTypeOwnership {

    @Inject
    public AssetTypeOwnershipDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public AssetTypeOwnership new_() {
        final AssetTypeOwnership operation = super.new_();
        operation.getProperty("role").setRequired(true);
        operation.getProperty("bu").setRequired(true);
        operation.getProperty("org").setRequired(true);
        return operation;
    }


    @Override
    protected IFetchProvider<AssetTypeOwnership> createFetchProvider() {
        return FETCH_PROVIDER;
       
    }
}
