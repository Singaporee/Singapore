package singapore.assets;

import singapore.services.ConditionRating;
import singapore.services.Servicestatus;
import ua.com.fielden.platform.entity.ActivatableAbstractEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Readonly;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Asset Number")
@CompanionObject(IAsset.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
public class Asset extends ActivatableAbstractEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Asset.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "regulatory", desc = "Indicated if asset is regulatory")
    private boolean regulatory;

    @IsProperty
    @MapTo
    @Title(value = "Number", desc = "A unique asset number, auto-generated.")
    @CompositeKeyMember(1)
    @Readonly
    private String number;

    @IsProperty
    @MapTo
    @Title(value = "Condition Rating", desc = "The class of this asset type.")
    private ConditionRating conditionRating;

    @Observable
    public Asset setConditionRating(final ConditionRating conditionRating) {
        this.conditionRating = conditionRating;
        return this;
    }

    public ConditionRating getConditionRating() {
        return conditionRating;
    }

    @IsProperty
    @MapTo
    @Title(value = "Service status", desc = "The class of this asset type.")
    private Servicestatus servicestatus;

    @Observable
    public Asset setServicestatus(final Servicestatus servicestatus) {
        this.servicestatus = servicestatus;
        return this;
    }

    public Servicestatus getServicestatus() {
        return servicestatus;
    }

    
    @Observable
    public Asset setNumber(final String number) {
        this.number = number;
        return this;
    }

    public String getNumber() {
        return number;
    }
    @Override
    @Observable
    public Asset setActive(boolean active) {
        super.setActive(active);
        return this;
    }
    
    
    @Observable
    public Asset setRegulatory(final boolean regulatory) {
        this.regulatory = regulatory;
        return this;
    }


    public boolean getRegulatory() {
        return regulatory;
    
    }
}
    

