package com.xlu.wanandroidmvvm.adapter

import android.view.LayoutInflater
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.google.android.flexbox.FlexboxLayout
import com.xlu.kotlinandretrofit.bean.Article
import com.xlu.kotlinandretrofit.bean.SystemBean
import com.xlu.wanandroidmvvm.R
import java.text.FieldPosition

/**
 * @Author xlu
 * @Date 2020/7/27 22:15
 * @Description TODO
 */
class NavigationAdapter(data:MutableList<SystemBean>) : BaseQuickAdapter<SystemBean, BaseViewHolder>(R.layout.item_navigation,data){

    private var mInflater: LayoutInflater? = null
    //private val mFlexItemTextViewCaches: Queue<TextView> = LinkedList()

    private var listener: NavigationAdapter.clickListener?=null

    fun setListener(listener: NavigationAdapter.clickListener?) {
        this.listener = listener
    }

    override fun convert(holder: BaseViewHolder, item: SystemBean) {
        holder.setText(R.id.title,item.name)

        val flexbox: FlexboxLayout = holder.getView(R.id.flexBox)
        flexbox.removeAllViews()
        for (i in item.children) {
            val name = i.name
            val child = createFlexItemTextView(flexbox)
            child!!.text = name
            child.setOnClickListener { listener?.clicked(holder.adapterPosition,i.id,i.name)}
            flexbox.addView(child)
        }

    }

    interface clickListener {
        fun clicked(position: Int,id:Int,name:String)
    }

/*
    private fun createOrGetCacheFlexItemTextView(fbl: FlexboxLayout): TextView? {
        val tv = mFlexItemTextViewCaches.poll()
        return createFlexItemTextView(fbl)
    }
*/

    private fun createFlexItemTextView(fbl: FlexboxLayout): TextView? {
        val mInflater: LayoutInflater = LayoutInflater.from(fbl.context)

        return mInflater.inflate(R.layout.item_nav_flexitem, fbl, false) as TextView
    }


}