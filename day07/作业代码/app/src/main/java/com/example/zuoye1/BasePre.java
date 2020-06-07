package com.example.zuoye1;

public class BasePre<V extends BaseView>  {
   public V view;
    public void bindView(V v){
        this.view= v;
    }
}
