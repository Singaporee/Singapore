package singapore.users;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssetOperator}.
 *
 * @author Developers
 *
 */
@EntityType(AssetOperator.class)
public class AssetOperatorDao extends CommonEntityDao<AssetOperator> implements IAssetOperator {

    @Inject
    public AssetOperatorDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public AssetOperator new_() {
        final AssetOperator operation = super.new_();
        operation.getProperty("role").setRequired(true);
        operation.getProperty("bu").setRequired(true);
        operation.getProperty("org").setRequired(true);
        return operation;
    }


    @Override
    protected IFetchProvider<AssetOperator> createFetchProvider() {
            return FETCH_PROVIDER;

    }
}
