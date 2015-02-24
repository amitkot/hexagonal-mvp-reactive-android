package es.ffgiraldez.hmr.android.persistence;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import es.ffgiraldez.hmr.books.Book;
import es.ffgiraldez.hmr.recents.RecentRepository;
/**
 * @author Fernando Franco Giráldez
 */
@Module(
        library = true,
        complete = false
)
public class PersistenceModule {
    // ----------------------------------
    // PUBLIC API
    // ----------------------------------
    @Provides
    @Singleton
    public ModelSqliteHelper providesHelper(final Context context) {
        return new ModelSqliteHelper(context);
    }

    @Provides
    @Singleton
    @SuppressWarnings({"unchecked"})
    public RuntimeExceptionDao<Book, String> providesVideoDao(final ModelSqliteHelper helper) {
        return helper.getRuntimeExceptionDao(Book.class);
    }

    @Provides
    @Singleton
    public RecentRepository providesFeedRepository(final RuntimeExceptionDao<Book, String> bookDao) {
        return new OrmRecentRepository(bookDao);
    }
}
