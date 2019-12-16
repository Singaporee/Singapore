package singapore.security.tokens.persistent;

import static java.lang.String.format;

import ua.com.fielden.platform.reflection.TitlesDescsGetter;
import ua.com.fielden.platform.security.tokens.Template;
import singapore.assets.Assets;
/**
 * A security token for entity {@link Assets} to guard Save.
 *
 * @author Developers
 *
 */
public class Assets_CanSave_Token extends ???ModuleToken {
    public final static String TITLE = format(Template.SAVE.forTitle(), Assets.ENTITY_TITLE);
    public final static String DESC = format(Template.SAVE.forDesc(), Assets.ENTITY_TITLE);
}
