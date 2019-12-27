package singapore.assets;

import com.google.inject.Inject;

import ua.com.fielden.platform.entity.fetch.IFetchProvider;
import ua.com.fielden.platform.dao.CommonEntityDao;
import ua.com.fielden.platform.entity.query.IFilter;
import ua.com.fielden.platform.entity.annotation.EntityType;
/**
 * DAO implementation for companion object {@link IUsageRate}.
 *
 * @author Developers
 *
 */
@EntityType(UsageRate.class)
public class UsageRateDao extends CommonEntityDao<UsageRate> implements IUsageRate {

    @Inject
    public UsageRateDao(final IFilter filter) {
        super(filter);
    }
    
    @Override
    public UsageRate new_() {
        final UsageRate operation = super.new_();
        operation.getProperty("usagerate").setRequired(true);
        return operation;
    }

    @Override
    protected IFetchProvider<UsageRate> createFetchProvider() {
        return FETCH_PROVIDER;

    }
}
