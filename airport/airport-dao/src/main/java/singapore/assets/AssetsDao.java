package singapore.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import singapore.security.tokens.persistent.Assets_CanSave_Token;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAssets}.
 *
 * @author Developers
 *
 */
@EntityType(Assets.class)
public class AssetsDao extends CommonEntityDao<Assets> implements IAssets {

    @Inject
    public AssetsDao(final IFilter filter) {
        super(filter);
    }

    @Override
    @SessionRequired
    @Authorise(Assets_CanSave_Token.class)
    public Assets save(Assets entity) {
        return super.save(entity);
    }

    @Override
    protected IFetchProvider<Assets> createFetchProvider() {
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAssets.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAssets.FETCH_PROVIDER");
    }
}
