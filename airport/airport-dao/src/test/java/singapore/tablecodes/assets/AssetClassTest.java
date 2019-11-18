package singapore.tablecodes.assets;

import static org.junit.Assert.*;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.fetch;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.from;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.orderBy;
import static ua.com.fielden.platform.entity.query.fluent.EntityQueryUtils.select;

import java.math.BigDecimal;

import org.junit.Ignore;
import org.junit.Test;

import ua.com.fielden.platform.dao.QueryExecutionModel;
import ua.com.fielden.platform.entity.query.fluent.fetch;
import ua.com.fielden.platform.entity.query.model.EntityResultQueryModel;
import ua.com.fielden.platform.entity.query.model.OrderingModel;
import ua.com.fielden.platform.security.user.User;
import ua.com.fielden.platform.utils.IUniversalConstants;
import singapore.asset.tablecodes.AssetClass;
import singapore.asset.tablecodes.IAssetClass;
import singapore.personnel.Person;
import singapore.test_config.AbstractDaoTestCase;
import singapore.test_config.UniversalConstantsForTesting;

/**
 * Thi is a test case for {@link AssetClass}
 * 
 * @author Singapore
 *
 */
public class AssetClassTest extends AbstractDaoTestCase {
    
    @Test
    public void there_are_two_instances_in_test_population() {
        assertTrue(co(AssetClass.class).count(select(AssetClass.class).model()) == 2);
    }

    @Test
    public void there_is_only_one_instance_ending_with_2() {
        assertTrue(co(AssetClass.class).count(select(AssetClass.class).where().prop("name").like().val("%2").model()) == 1);
    }
    
    @Test
    public void there_is_only_one_instance_ending_with_2_even_if_conditioning_key() {
        assertTrue(co(AssetClass.class).count(select(AssetClass.class).where().prop("key").like().val("%2").model()) == 1);
    }
    
    
    @Test
    public void some_random_operations() {
        final AssetClass ac1 = co(AssetClass.class).findByKey("AC1");
        assertNotNull(ac1);
        assertEquals("AC1", ac1.getKey().toString());

        final IAssetClass coAssetClass = co(AssetClass.class);
        final AssetClass newAc3 = (AssetClass) coAssetClass.new_().setName("AC3");

        newAc3.setDesc("some value");
        newAc3.setDesc(null);

        assertFalse(newAc3.getProperty("desc").isValid());
        assertEquals("some value",newAc3.getDesc());
        
        newAc3.getProperty("desc").resetValidationResult();

        co(AssetClass.class).save(newAc3);

        final AssetClass ac3 = co(AssetClass.class).findByKey("AC3");
        assertNotNull(ac3);
        assertEquals("AC3", ac3.getName());
    }
    
    
 
    @Override
    public boolean saveDataPopulationScriptToFile() {
        return false;
    }

   
    @Override
    public boolean useSavedDataPopulationScript() {
        return false;
    }

    
    @Override
    protected void populateDomain() {
        // Need to invoke super to create a test user that is responsible for data population 
    	super.populateDomain();

    	// Here is how the Test Case universal constants can be set.
    	// In this case the notion of now is overridden, which makes it possible to have an invariant system-time.
    	// However, the now value should be after AbstractDaoTestCase.prePopulateNow in order not to introduce any date-related conflicts.
    	final UniversalConstantsForTesting constants = (UniversalConstantsForTesting) getInstance(IUniversalConstants.class);
    	constants.setNow(dateTime("2019-10-01 11:30:00"));

    	// If the use of saved data population script is indicated then there is no need to proceed with any further data population logic.
        if (useSavedDataPopulationScript()) {
            return;
        }

    	// Here the three Person entities are persisted using the the inherited from TG testing framework methods.
        save(new_composite(AssetClass.class, "AC1").setDesc("The first asset class"));
        save(new_composite(AssetClass.class, "AC2").setDesc("The second asset class"));
    }

}
