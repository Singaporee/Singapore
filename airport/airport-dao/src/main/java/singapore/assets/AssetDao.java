package singapore.assets;

import com.google.inject.Inject;
<<<<<<< HEAD

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.security.Authorise;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
import singapore.security.tokens.persistent.Asset_CanSave_Token;
=======
import ua.com.fielden.platform.keygen.IKeyNumber;
import ua.com.fielden.platform.keygen.KeyNumber;

import java.util.Collection;
import java.util.List;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.annotations.SessionRequired;
>>>>>>> Issue-#15
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IAsset}.
 *
 * @author Developers
 *
 */
<<<<<<< HEAD
@EntityType(Asset.class)
public class AssetDao extends CommonEntityDao<Asset> implements IAsset {
=======
/**
 * DAO implementation for companion object {@link IAsset}.
 *
 * @author Developers
 *
 */
@EntityType(Asset.class)
public class AssetDao extends CommonEntityDao<Asset> implements IAsset {
    public static final String DEFAULT_ASSET_NUMBER = "NEXT NUMBER WILL BE GENERATED UPON SAVE";
>>>>>>> Issue-#15

    @Inject
    public AssetDao(final IFilter filter) {
        super(filter);
    }
<<<<<<< HEAD

    @Override
    @SessionRequired
    @Authorise(Asset_CanSave_Token.class)
    public Asset save(Asset entity) {
        return super.save(entity);
=======
    
    @Override
    @SessionRequired
    public Asset save(final Asset asset) {
        final boolean wasPersisted = asset.isPersisted();
        try {
            if (!wasPersisted) {
                final IKeyNumber coKeyNumber = co(KeyNumber.class);
                final Integer nextNumber = coKeyNumber.nextNumber("ASSET_NUMBER");
                asset.setNumber(nextNumber.toString());
            }

            // save asset
            final Asset savedAsset = super.save(asset);
            if (!wasPersisted) {
                final AssetFinDet finDet = co(AssetFinDet.class).new_().setKey(savedAsset);
                co$(AssetFinDet.class).save(finDet);
            }
            // if no exception occurred then simply return the saved instance
            return savedAsset;
        } catch (final Exception ex) {
            // if there was an exception when saving a new asset we need reset the value of its number to the default value
            if (!wasPersisted) {
                asset.setNumber(DEFAULT_ASSET_NUMBER);
            }
            // and re-throw the exception
            throw ex;
        }
    }
    
    @Override
    public Asset new_() {
        final Asset asset = super.new_();
        asset.setNumber(DEFAULT_ASSET_NUMBER);
        return asset;
    }

    @Override
    @SessionRequired
    public int batchDelete(final Collection<Long> entitiesIds) {
        return defaultBatchDelete(entitiesIds);
    }

    @Override
    @SessionRequired
    public int batchDelete(final List<Asset> entities) {
        return defaultBatchDelete(entities);
>>>>>>> Issue-#15
    }

    @Override
    protected IFetchProvider<Asset> createFetchProvider() {
<<<<<<< HEAD
        // TODO: uncomment the following line and specify the properties, which are required for the UI in IAsset.FETCH_PROVIDER. Then remove the line after.
        // return FETCH_PROVIDER;
        throw new UnsupportedOperationException("Please specify the properties, which are required for the UI in IAsset.FETCH_PROVIDER");
    }
}
=======
        return FETCH_PROVIDER;
    }
}
>>>>>>> Issue-#15
