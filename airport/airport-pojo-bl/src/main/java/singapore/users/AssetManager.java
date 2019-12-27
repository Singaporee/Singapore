package singapore.users;

import java.util.Date;

import singapore.assets.Asset;
import singapore.assets.definers.AssetManagerExclusivityDefiner;
import singapore.organizational.BusinessUnit;
import singapore.organizational.Organisation;
import singapore.organizational.Role;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.entity.annotation.mutator.AfterChange;
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
@CompanionObject(IAssetManager.class)
public class AssetManager extends AbstractPersistentEntity<Asset> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(AssetManager.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private Asset key;
    
    @Override
    @Observable
    public AssetManager setKey(final Asset key) {
        this.key = key;
        return this;
    }

    @Override
    public Asset getKey() {
        return key;
    }
    

    @IsProperty
    @MapTo
    @Title(value = "Role", desc = "Role that owns assets of the specified asset type")
    @AfterChange(AssetManagerExclusivityDefiner.class)
    private Role role;
    
    @Observable
    public AssetManager setRole(final Role role) {
        this.role = role;
        return this;
    }

    public Role getRole() {
        return role;
    }
    

    @IsProperty
    @MapTo
    @Title(value = "Business Unit", desc = "Business unit that owns assets of the specified asset type")
    @AfterChange(AssetManagerExclusivityDefiner.class)
    private BusinessUnit bu;
    
    
    @Observable
    public AssetManager setBu(final BusinessUnit bu) {
        this.bu = bu;
        return this;
    }

    public BusinessUnit getBu() {
        return bu;
    }


    @IsProperty
    @MapTo
    @Title(value = "Organisation", desc = "Organisation that owns assets of the specified asset type")
    @AfterChange(AssetManagerExclusivityDefiner.class)
    private Organisation org;

    @Observable
    public AssetManager setOrg(final Organisation org) {
        this.org = org;
        return this;
    }

    public Organisation getOrg() {
        return org;
    }


    
    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "The start date of the ownership")
    @CompositeKeyMember(2)
    @DateOnly
    private Date startDate;
   

    @Observable
    public AssetManager setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }

}
