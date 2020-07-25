package com.xlu.wanandroidmvvm.adapter;


import android.graphics.Movie;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.xlu.kotlinandretrofit.bean.Article;
import com.xlu.wanandroidmvvm.R;
import com.xlu.wanandroidmvvm.common.OnChildItemClickListener;
import com.xlu.wanandroidmvvm.databinding.ItemArticleBinding;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class RecyclerDataBindingAdapter extends BaseQuickAdapter<Article.Data, BaseDataBindingHolder<ItemArticleBinding>> {


    public RecyclerDataBindingAdapter() {
        super(R.layout.item_article);
    }

    private likeListener listener = null;

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemArticleBinding> itemArticleBindingBaseDataBindingHolder, Article.Data data) {
        // 获取 Binding
        ItemArticleBinding binding = itemArticleBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            binding.setArticle(data);
            binding.executePendingBindings();
            /*我看懂了！这一招叫移花接木*/
            binding.ivLike.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    listener.like(data,itemArticleBindingBaseDataBindingHolder.getAdapterPosition());
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    listener.unlike(data,itemArticleBindingBaseDataBindingHolder.getAdapterPosition());
                }
            });
        }
    }

    public void setListener(likeListener listener) {
        this.listener = listener;
    }

    public interface likeListener {
        public void like(Article.Data data,int position);
        public void unlike(Article.Data data,int position);
    }

}
