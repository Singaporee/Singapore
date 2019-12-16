package singapore.assets;

import ua.com.fielden.platform.entity.NoKey;
import static ua.com.fielden.platform.entity.NoKey.NO_KEY;
import singapore.assets.Assets;
import ua.com.fielden.platform.entity.AbstractFunctionalEntityWithCentreContext;
import ua.com.fielden.platform.entity.annotation.KeyType;
import ua.com.fielden.platform.entity.annotation.CompanionObject;
import ua.com.fielden.platform.entity.annotation.IsProperty;
import ua.com.fielden.platform.entity.annotation.Observable;
import ua.com.fielden.platform.entity.annotation.SkipEntityExistsValidation;

/**
 * Locator entity object.
 *
 * @author Developers
 *
 */
@KeyType(NoKey.class)
@CompanionObject(IAssetsLocator.class)
public class AssetsLocator extends AbstractFunctionalEntityWithCentreContext<NoKey> {

    public AssetsLocator() {
        setKey(NO_KEY);
    }

    @IsProperty
    @SkipEntityExistsValidation(skipActiveOnly = true)
    private Assets assets;

    @Observable
    public AssetsLocator setAssets(final Assets value) {
        this.assets = value;
        return this;
    }

    public Assets getAssets() {
        return assets;
    }

}
