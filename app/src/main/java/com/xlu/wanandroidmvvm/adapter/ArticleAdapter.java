package com.xlu.wanandroidmvvm.adapter;


import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseDataBindingHolder;
import com.like.LikeButton;
import com.like.OnLikeListener;
import com.xlu.kotlinandretrofit.bean.Article;
import com.xlu.wanandroidmvvm.R;
import com.xlu.wanandroidmvvm.databinding.ItemArticleBinding;
import com.xlu.wanandroidmvvm.databinding.ItemProjectBinding;

import org.jetbrains.annotations.NotNull;


public class ArticleAdapter extends BaseQuickAdapter<Article.Data, BaseDataBindingHolder<ItemProjectBinding>> {


    public ArticleAdapter() {
        super(R.layout.item_project);
    }

    private likeListener listener = null;

    public void setListener(likeListener listener) {
        this.listener = listener;
    }

    @Override
    protected void convert(@NotNull BaseDataBindingHolder<ItemProjectBinding> itemProjectBindingBaseDataBindingHolder, Article.Data data) {
        ItemProjectBinding binding = itemProjectBindingBaseDataBindingHolder.getDataBinding();
        if (binding != null) {
            binding.setArticle(data);
            binding.executePendingBindings();
            /*我看懂了！这一招叫移花接木*/
            binding.ivLike.setOnLikeListener(new OnLikeListener() {
                @Override
                public void liked(LikeButton likeButton) {
                    listener.like(data,itemProjectBindingBaseDataBindingHolder.getAdapterPosition());
                }

                @Override
                public void unLiked(LikeButton likeButton) {
                    listener.unlike(data,itemProjectBindingBaseDataBindingHolder.getAdapterPosition());
                }
            });
            binding.tvTitle.setText(Html.fromHtml(data.getTitle()));
            String url = data.getEnvelopePic();
            if (url.length()==0){
                binding.image.setVisibility(View.GONE);
            }else {
                Glide.with(getContext())
                        .load(url)
                        .into(binding.image);
            }

        }
    }

    public interface likeListener {
        public void like(Article.Data data, int position);
        public void unlike(Article.Data data, int position);
    }

    public Bundle getBundle(int position){
        Bundle bundle = new Bundle();
        bundle.putString("loadUrl", getData().get(position).getLink());
        bundle.putString("title", getData().get(position).getTitle());
        bundle.putString("author", getData().get(position).getAuthor());
        bundle.putInt("id", getData().get(position).getId());
        return bundle;
    }


}
