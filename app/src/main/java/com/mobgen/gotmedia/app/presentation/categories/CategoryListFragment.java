package com.mobgen.gotmedia.app.presentation.categories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v17.leanback.widget.ClassPresenterSelector;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobgen.gotmedia.R;
import com.mobgen.gotmedia.app.domain.categories.enums.CategoryType;
import com.mobgen.gotmedia.app.entity.categories.Book;
import com.mobgen.gotmedia.app.presentation.categories.listcell.BookCell;
import com.mobgen.gotmedia.app.presentation.categories.pojo.CategoryItem;
import com.mobgen.gotmedia.app.presentation.categories.presenter.CategoriesContract;
import com.mobgen.gotmedia.core.adapter.GotArrayObjectAdapter;
import com.mobgen.gotmedia.core.adapter.RecyclerViewAdapter;
import com.mobgen.gotmedia.core.fragment.base.FragmentBase;
import com.mobgen.gotmedia.core.utilities.UiUtil;

import java.util.List;

import javax.inject.Inject;

/**
 * Created on 3/6/18.
 */

public class CategoryListFragment extends FragmentBase {


    public static final String TAG = "CategoryListFragment";
    public static final String ITEM = "item";
    public static final double MIN_RATING = 1d;
    public static final String Y_LOC = "yLoc";
    public static final int DURATION = 600;
    private RecyclerView list;
    private RelativeLayout header;
    private GotArrayObjectAdapter adapterItems;
    private RecyclerViewAdapter adapter;
    private int yLoc;
    private CategoryItem item;
    private TextView desc;
    private View buttonClose;
    @Inject
    protected CategoriesContract.CategoryListPresenter presenter;
    private View progressBar;
    private TextView errorText;
    private Button errorButton;
    private String errorStr;

    class HeaderAnimAdapter extends AnimatorListenerAdapter {
        HeaderAnimAdapter() {
        }

        public void onAnimationEnd(Animator animation) {
            header.clearAnimation();
            buttonClose.setVisibility(View.VISIBLE);
            presenter.visualizeData(item, adapterItems.size());
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public static CategoryListFragment newInstance(CategoryItem item, int yLoc) {
        CategoryListFragment fragment = new CategoryListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ITEM, item);
        bundle.putInt(Y_LOC, yLoc);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    public CategoryListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        yLoc = getArguments().getInt(Y_LOC);
        yLoc = (int) (yLoc - getResources().getDimension(R.dimen.default_padding));
        item = getArguments().getParcelable(ITEM);
        setErrorText(item.getTitle());
    }

    private void setErrorText(String title) {
        CategoryType type = CategoryType.fromString(title);
        switch (type){
            case BOOKS:
                errorStr = getString(R.string.there_was_an_error_loading_books);
                break;
            case HOUSES:
                errorStr = getString(R.string.there_was_an_error_loading_houses);
                break;
            case CHARACTERS:
                errorStr = getString(R.string.there_was_an_error_loading_characters);
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_item_fragment, container, false);
        list = view.findViewById(R.id.list);
        header = view.findViewById(R.id.header);
        progressBar = view.findViewById(R.id.progressBar);
        buttonClose = view.findViewById(R.id.searchBoxClear);
        adapterItems = new GotArrayObjectAdapter();
        adapter = new RecyclerViewAdapter(adapterItems, getListPresenter());
        list.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        list.setAdapter(adapter);
        desc = view.findViewById(R.id.description);
        errorText = view.findViewById(R.id.errorText);
        errorButton = view.findViewById(R.id.errorButton);
        initViews();
        presenter.takeView(this);
        return view;
    }

    private void initViews() {
        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragment().getChildFragmentManager().popBackStackImmediate();
            }
        });
        desc.setText(item.getTitle());
        errorText.setText(errorStr);
        errorText.setTextColor(ContextCompat.getColor(getContext(), R.color.white_70));
        errorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.visualizeData(item, adapterItems.size());
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        emulateCardViewTransformsAndStartAnimations(header, list, true, yLoc);
    }

    private PresenterSelector getListPresenter() {
        ClassPresenterSelector selector = new ClassPresenterSelector();
        selector.addClassPresenter(Book.class, new BookCell());
        return selector;
    }


    public void updateState(boolean isError) {
        if(isError){
            list.setVisibility(View.GONE);
            errorText.setVisibility(View.VISIBLE);
            errorButton.setVisibility(View.VISIBLE);
        }else {
            if(list.getVisibility() != View.VISIBLE){
                list.setVisibility(View.VISIBLE);
            }
            if(errorText.getVisibility() != View.GONE){
                errorText.setVisibility(View.GONE);
            }
            if(errorButton.getVisibility() != View.GONE){
                errorButton.setVisibility(View.GONE);
            }
        }
        progressBar.setVisibility(View.GONE);
    }

    private void emulateCardViewTransformsAndStartAnimations(final RelativeLayout header, final RecyclerView recyclerView,
                                                             boolean isAnimateIn, final int animationStartY) {
        if (header.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams headerParams = (RelativeLayout.LayoutParams) header.getLayoutParams();
            UiUtil.setMargin(headerParams, headerParams.getMarginStart() + additionalPaddingOnTheSide(), 0,
                    headerParams.getMarginEnd() + additionalPaddingOnTheSide(), 0);
            header.setLayoutParams(headerParams);
            RelativeLayout.LayoutParams recyclerViewParams = (RelativeLayout.LayoutParams) recyclerView.getLayoutParams();
            UiUtil.setMargin(recyclerViewParams, recyclerViewParams.getMarginStart() + additionalPaddingOnTheSide(), 0,
                    recyclerViewParams.getMarginEnd() + additionalPaddingOnTheSide(), 0);
            recyclerView.setLayoutParams(recyclerViewParams);
        }
        if (isAnimateIn) {
            header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    header.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    Rect rectf = new Rect();
                    header.getGlobalVisibleRect(rectf);
                    int translateY = Math.max(animationStartY, rectf.top) - Math.min(animationStartY, rectf.top);
                    header.animate().translationY((float) translateY).setDuration(0).start();
                    recyclerView.animate().translationY((float) translateY).setDuration(0).start();
                    animateHeader();
                }
            });
        }
    }

    private void animateHeader() {
        header.animate().translationY(0.0f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(DURATION)
                .setListener(new HeaderAnimAdapter()).start();
        list.animate().translationY(0.0f).setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(DURATION)
                .setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        list.clearAnimation();
                    }
                }).start();
    }


    private int additionalPaddingOnTheSide() {
        return (int) (((double) getResources().getDimension(R.dimen.card_elevation)) + ((MIN_RATING - Math.cos(45.0d)) *
                ((double) getResources().getDimension(R.dimen.card_corner_radius))));
    }

    public void showData(List<Object> categoriesResults) {
        adapterItems.addAll(adapterItems.size(), categoriesResults);
    }
}
