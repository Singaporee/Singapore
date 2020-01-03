package singapore.assets;

import java.util.Date;

import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(Asset.class)
@KeyTitle("Asset")
@CompanionObject(IUsageRate.class)
@MapEntityTo
public class UsageRate extends AbstractPersistentEntity<Asset> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(UsageRate.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private Asset key;
    
    @Override
    @Observable
    public UsageRate setKey(final Asset key) {
        this.key = key;
        return this;
    }

    @Override
    public Asset getKey() {
        return key;
    }
    
    
    
    @IsProperty
    @MapTo
    @Title(value = "Plan Date", desc = "The plan date of the repair")
    @CompositeKeyMember(2)
    @DateOnly
    private Date planDate;
   

    @Observable
    public UsageRate setPlanDate(final Date planDate) {
        this.planDate = planDate;
        return this;
    }

    public Date getPlanDate() {
        return planDate;
    }
    
    @IsProperty
    @MapTo
    @Title(value = "Usage Rate", desc = "usage rate of Asset")
    private String usagerate;

    @Observable
    public UsageRate setUsagerate(final String usagerate) {
        this.usagerate = usagerate;
        return this;
    }

    public String getUsagerate() {
        return usagerate;
    }

    
}    
    
    
    
