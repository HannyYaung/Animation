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
简单介绍一下每一个Interpolator

#### AccelerateDecelerateInterpolator
> 先加速再减速.这是默认的Interpolater,也就是说你不设置的话,那么动画将会使用这个Interpolator

**物体看起来从速度为0开始逐渐加速,然后再逐渐减速直到0的运动 正玄/余弦曲线**

**用途:** 像上面说的,它是一种最符合物理世界的模型,所以如果做最简单的状态变化(位移,缩放,旋转等等),那么一般不用去设置Interpolator,就用这个默认最好.

#### LinearInterpalotor
匀速.

#### AccelerateInterpolator

持续加速

在整个动画过程中,一直在加速,直到动画结束的一瞬间,直接停止.

**用途:** 这个神经病模型,主要用在离散效果中,比如物体从界面中飞离

#### DecelerateInterpolator
持续减速直到0.

动画开始的时候是最高速度,然后在动画过程中逐渐减速,直到动画结束的时候恰好减速到0

**用途:** 和上面的飞离恰恰相反,这个用于进场

#### AnticipateInterpolator
这个效果看起来有点像是投掷物体或者跳跃等动作前的蓄力

#### OvershootInterpolater

动画会超过目标值一些,然后再弹回来.效果看起来有点像一屁股坐在沙发后又被弹起来一点的感觉.

#### AnticipateOvershootInterpolater

是上面回拉和回弹的结合版.

#### BounceInterpolater 
在目标值出弹跳,有点像玻璃球掉在地板上的效果.

#### CycleInterpolater

这个也是一个正玄/余弦曲线,不过它和AccelerateDecelerateInterpolator的区别是,它可以自定义曲线的周期,所以可以不到终点就结束,也可以到达终点后回弹,回弹的次数由曲线的周期决定,曲线的周期有CecleInterpalter()的构造方法参数决定.

> 参数为0.5f

#### PathInterpolater

自定义动画完成度/时间完成度曲线.
用这个Interpolater 你可以制作出任何你想要的速度模型,定制的方式是使用一个path对象来绘制出你要的动画完成度/时间完成度曲线.例如:

### FastOutLinearInInterpolater

加速运动.

这个Interpolater 的作用是加速运动.这个用的是按照贝塞尔曲线加速,而AccelerateInterpolator是指数加速.具体来说就是Fast的前期加速要更快一些

### FastOutSlowInterpolator

先加速再减速 
同样也是和前面的AccelerateDecelerateInterpolator的区别是前者是贝塞尔曲线,后者是正玄余弦曲线,具体的就是前者前期加速更快一些

### LinerOutSlowInInterpolater 
持续减速

它和DecelerateInterpolator 比起来,同为减速曲线.主要在于LinerOutSlowInInterpolaterd的初始速度更高

## 设置监听器

> 设置监听器的方法,ViewpropertyAnimator 和 ObjectAnimator 略微不同,ViewpropertyAnimator用的是setListener()和setUpdateListener()方法,可以设置一个监听器,移除要通过set(null)的方式移除,而ObjectiveAnimator则是用addListener()和addUpdateListener()来添加一个或多个监听器,通过remove来制定移除对象.