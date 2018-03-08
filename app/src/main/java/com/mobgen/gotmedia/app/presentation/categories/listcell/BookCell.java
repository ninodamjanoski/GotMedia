package com.mobgen.gotmedia.app.presentation.categories.listcell;

import android.support.v17.leanback.widget.Presenter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.entity.categories.Book;
import com.mobgen.gotmedia.app.presentation.view.TitleDescRowView;
import com.mobgen.gotmedia.core.lists.ChildClickPresenter;

import java.util.Date;
import java.util.List;

/**
 * Created on 3/7/18.
 */

public class BookCell extends ChildClickPresenter {

    public class BookCellViewHolder extends Presenter.ViewHolder implements BookViewHolder {

        private final TextView textTitle;
        private final TitleDescRowView textAuthors;
        private final TitleDescRowView textIsbn;
        private final TitleDescRowView textCountry;
        private final TitleDescRowView textMediatype;
        private final TitleDescRowView textNumPages;
        private final TitleDescRowView textPublisher;
        private final TitleDescRowView textDate;
        View view;
        public BookCellViewHolder(View view) {
            super(view);
            this.view = view;
            textTitle = view.findViewById(R.id.title);
            textAuthors = view.findViewById(R.id.authors);
            textIsbn = view.findViewById(R.id.isbn);
            textCountry = view.findViewById(R.id.country);
            textMediatype = view.findViewById(R.id.media_type);
            textNumPages = view.findViewById(R.id.num_pages);
            textPublisher = view.findViewById(R.id.publisher);
            textDate = view.findViewById(R.id.date);

        }

        @Override
        public void bindBookData(List<String> authors, String country, String isbn, String mediaType,
                                 int numPages, String name, String publisher, Date released) {
            textTitle.setText(name);
            setAuthors(authors);
            textCountry.setDesc(country);
            textIsbn.setDesc(isbn);
            textMediatype.setDesc(mediaType);
            textNumPages.setDesc(String.valueOf(numPages));
            textPublisher.setDesc(publisher);
            textDate.setDesc(released.toString());
        }

        private void setAuthors(List<String> authors) {
            String authorsText = "";
            for(String author : authors){
                if(!TextUtils.isEmpty(authorsText)){
                    authorsText += ", \n";
                }
                authorsText += author;
            }
            textAuthors.setDesc(authorsText);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return new BookCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_book,
                parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Book book = (Book) item;
        BookViewHolder holder = (BookViewHolder) viewHolder;
        holder.bindBookData(book.getAuthors(), book.getCountry(),book.getIsbn(), book.getMediaType(), book.getNumPages(),
                book.getName(), book.getPublisher(), book.getReleased());
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {

    }
}
