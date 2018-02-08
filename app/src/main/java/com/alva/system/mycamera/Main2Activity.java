package com.alva.system.mycamera;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import nl.dionsegijn.konfetti.models.Size;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;

public class Main2Activity extends AppCompatActivity {
    KonfettiView konfettiView;
    //https://github.com/DanielMartinus/Konfetti
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        konfettiView = findViewById(R.id.viewKonfetti);
        /**
         *
         */
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12,0.1f))
                .setPosition(-50f,  + 1050f, -50f, -50f)
                .stream(300, 50000L); //动画时间
        observable.subscribe(observer);
    }

    // 步骤1：创建被观察者 Observable & 生产事件
    // 即 顾客入饭店 - 坐下餐桌 - 点菜
    //  1. 创建被观察者 Observable 对象
    Disposable mDispoable;
    Observable<Integer>observable = Observable.create(new ObservableOnSubscribe<Integer>() {
        //2 .在复写的subscribe（）里面定义需要发送的事件
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            e.onNext(1);
            e.onNext(2);
            e.onNext(3);
            e.onComplete();
        }
    });
    Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {
            mDispoable = d;
            Log.d("yangjie","开始采集subscribe连接" );
        }

        @Override
        public void onNext(Integer value) {
            if(value==2){
                mDispoable.dispose();
                Log.d("yangjie","对onNext事件" +value+"作出响应 。 然后断开连接+ " + mDispoable.isDisposed() );
            }
        }

        @Override
        public void onError(Throwable e) {
            Log.d("yangjie","对onError事件作出响应");
        }

        @Override
        public void onComplete() {
            Log.d("yangjie","对onComlete事件作出响应");
        }
    };

}
