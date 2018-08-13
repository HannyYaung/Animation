## 动画分为两种 Animation 和 Transition
- Animation 分为View Animation 和 Property Animation两种动画 
- View Animation是纯粹基于FrameWork的绘制转变
- Property Animation 属性动画,是用的最多的一种方式
## ObjectAnimator
> 自定义使用方式步骤:
- 添加getter/setter方法
- 使用ObjectAnimator.ofXX() 创建objectAnimator对象
- 用start执行动画
```
public class SportsView extends View {  
     float progress = 0;

    ......

    // 创建 getter 方法
    public float getProgress() {
        return progress;
    }

    // 创建 setter 方法
    public void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        ......

        canvas.drawArc(arcRectF, 135, progress * 2.7f, false, paint);

        ......
    }
}

......

// 创建 ObjectAnimator 对象
ObjectAnimator animator = ObjectAnimator.ofFloat(view, "progress", 0, 65);  
// 执行动画
animator.start();  
```

## 通用功能

#### 1.setDuration(int duration)设置动画时长
单位是毫秒
```
// imageView1: 500 毫秒
imageView1.animate()  
        .translationX(500)
        .setDuration(500);

// imageView2: 2 秒
ObjectAnimator animator = ObjectAnimator.ofFloat(  
        imageView2, "translationX", 500);
animator.setDuration(2000);  
animator.start(); 
```

### 2. setInterpolator(Interpolator interpolator)设置Interpolator

- 速度设置器,填入不同的插入器,就会有不同的速度模型来执行