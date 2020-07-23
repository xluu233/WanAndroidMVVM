package com.xlu.wanandroidmvvm.adapter;


import android.graphics.Movie;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.xlu.kotlinandretrofit.bean.Article;
import com.xlu.wanandroidmvvm.R;
import com.xlu.wanandroidmvvm.databinding.ItemArticleBinding;

import org.jetbrains.annotations.NotNull;


public class RecyclerDataBindingAdapter extends BaseQuickAdapter<Article.Data, BaseDataBindingHolder<ItemArticleBinding>> {


    public RecyclerDataBindingAdapter() {
        super(R.layout.item_article);
    }


    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemArticleBinding> itemArticleBindingBaseDataBindingHolder, Article.Data data) {
        // 获取 Binding
        ItemArticleBinding binding = itemArticleBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            binding.setArticle(data);
            binding.executePendingBindings();
        }
    }
}
