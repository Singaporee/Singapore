package singapore.services;

import java.util.Date;

import singapore.asset.tablecodes.AssetClass;
import singapore.asset.tablecodes.AssetType;
import singapore.assets.Asset;
import singapore.users.AssetManager;
import ua.com.fielden.platform.entity.AbstractPersistentEntity;
import ua.com.fielden.platform.entity.DynamicEntityKey;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.CompositeKeyMember;
import ua.com.fielden.platform.entity.annotation.DateOnly;
import ua.com.fielden.platform.entity.annotation.DescRequired;
import ua.com.fielden.platform.entity.annotation.DescTitle;
import ua.com.fielden.platform.entity.annotation.DisplayDescription;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.KeyTitle;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.MapEntityTo;
import ua.com.fielden.platform.entity.annotation.MapTo;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.Title;
import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.utils.Pair;

/**
 * Master entity object.
 *
 * @author Developers
 *
 */
@KeyType(DynamicEntityKey.class)
@KeyTitle("Name")
@CompanionObject(IServicestatus.class)
@MapEntityTo
@DescTitle("Description")
@DisplayDescription
@DescRequired
public class Servicestatus extends AbstractPersistentEntity<DynamicEntityKey> {

    private static final Pair<String, String> entityTitleAndDesc = TitlesDescsGetter.getEntityTitleAndDesc(Servicestatus.class);
    public static final String ENTITY_TITLE = entityTitleAndDesc.getKey();
    public static final String ENTITY_DESC = entityTitleAndDesc.getValue();

    @IsProperty
    @MapTo
    @Title(value = "Service Status", desc = "Desc")
    @CompositeKeyMember(1)
    private String name;
    
    @IsProperty
    @MapTo
    @Title(value = "Asset", desc = "The asset")
    private Asset asset;

    @Observable
    public Servicestatus setAsset(final Asset asset) {
        this.asset = asset;
        return this;
    }

    public Asset getAsset() {
        return asset;
    }
    
    @IsProperty
    @MapTo
    @Title(value = "Start Date", desc = "The start date of the ownership")
    @DateOnly
    private Date startDate;
   
    
    @Observable
    public Servicestatus setStartDate(final Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getStartDate() {
        return startDate;
    }
    
    
    @Observable
    public Servicestatus setName(final String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    @Override
    @Observable
    public Servicestatus setDesc(String desc) {
        super.setDesc(desc);
        return this;
    }
    
    
    

}

