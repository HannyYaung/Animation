## 高级进阶--动画
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

## 3设置监听器

> 设置监听器的方法,ViewpropertyAnimator 和 ObjectAnimator 略微不同,ViewpropertyAnimator用的是setListener()和setUpdateListener()方法,可以设置一个监听器,移除要通过set(null)的方式移除,而ObjectiveAnimator则是用addListener()和addUpdateListener()来添加一个或多个监听器,通过remove来制定移除对象.
另外,由于ObjectAnimator 支持使用pause() 方法暂停,所以它还多了一个addPauseListerner/removePauseListener() 的支持; 而ViewpropertyAnimator 则独有withStartAnimator 和 withEndAnimator 方法,可以设置一次动画开始或者结束的监听.

###  3.1 ViewPropertyAnimator.setListener()/ObjectAnimator.addListener()

这两个方法的名称不一样,可以设置的监听器数量也不一样,但他们参数类型都是AnimatorListener,本质都是一样的.AnimatorListener 共有四个回调方法:

####  3.1.1 onAnimationStart(Animator animation)

当动画开始的时候方法被调用

####  3.1.2 onAnimationEnd(Animator animation)

当动画结束的时候被调用

####  3.1.3 onAnimationCancel(Animator animation)

当动画被通过cancel()方法取消时,这个方法被调用.

#### 3.1.4 onAnimatorRepeat(Animator animatio)

当动画通过 setRepeatMode 和 setRepeatCount() 或 repeat() 方法重复执行时.这个方法被调用.由于ViewPropertyAnimator不支持重复,所以这个方法不适用

###  3.2 ViewPropertyAnimator.setUpdateListener() / ObjectAnimator.addUpdateListener()

和上面 3.1 的两个方法一样，这两个方法虽然名称和可设置的监听器数量不一样，但本质其实都一样的，它们的参数都是 AnimatorUpdateListener。它只有一个回调

方法：onAnimationUpdate(ValueAnimator animation)。

#### 3.2.1 onAnimationUpdate(ValueAnimator animation)

当动画的属性更新时（不严谨的说，即每过 10 毫秒，动画的完成度更新时），这个方法被调用。

方法的参数是一个 ValueAnimator，ValueAnimator 是 ObjectAnimator 的父类，也是 ViewPropertyAnimator 的内部实现，所以这个参数其实就是 ViewPropertyAnimator 内部的那个 ValueAnimator，或者对于  ObjectAnimator 来说就是它自己本身。

ValueAnimator 有很多方法可以用，它可以查看当前的动画完成度、当前的属性值等等。不过  ValueAnimator 是下一期才讲的内容，所以这期就不多说了。

###  3.3 ObjectAnimator.addPauseListener()

由于 ObjectAnimator.pause() 是下期的内容，所以这个方法在这期就不讲了。当然，如果你有兴趣的话，现在就了解一下也可以。

###  3.3 ViewPropertyAnimator.withStartAction/EndAction()

这两个方法是 ViewPropertyAnimator 的独有方法。它们和 set/addListener() 中回调的 onAnimationStart() /  onAnimationEnd() 相比起来的不同主要有两点：

withStartAction() / withEndAction() 是一次性的，在动画执行结束后就自动弃掉了，就算之后再重用  ViewPropertyAnimator 来做别的动画，用它们设置的回调也不会再被调用。而 set/addListener() 所设置的 AnimatorListener 是持续有效的，当动画重复执行时，回调总会被调用。

withEndAction() 设置的回调只有在动画正常结束时才会被调用，而在动画被取消时不会被执行。这点和 AnimatorListener.onAnimationEnd() 的行为是不一致的。


## TypeEvalutor

关于ObjectAnimator,可以用ofInt来做整数属性动画,onFloat()来做小数属性动画.这两种属性类型可以满足日常使用开发,如果用到其他类型属性动画的时候,就需要用到TypeEvaluator 了.

### ArgbEvaluator

TypeEvaluator最经典的用法是使用ArgbEvalutor来做颜色渐变动画
```
  ObjectAnimator animator = ObjectAnimator.ofInt(argbCircle,"color",0xffff0000,0xff00ff00);
                animator.setEvaluator(new ArgbEvaluator());
                animator.setDuration(3000);
                animator.start();
```
在Android5.0 Api21 上面可以直接使用ObjectAnimator.ofArgb
```
   ObjectAnimator animator = ObjectAnimator.ofArgb(argbCircle,"color",0xffff0000,0xff00ff00);
                animator.setDuration(3000);
                animator.start();
                
```
## 自定义Evaluator 

如果你对 ArgbEvaluator 的效果不满意,可以进行自定义一个:
```
// 自定义 HslEvaluator
private class HsvEvaluator implements TypeEvaluator<Integer> {  
   float[] startHsv = new float[3];
   float[] endHsv = new float[3];
   float[] outHsv = new float[3];

   @Override
   public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
       // 把 ARGB 转换成 HSV
       Color.colorToHSV(startValue, startHsv);
       Color.colorToHSV(endValue, endHsv);

       // 计算当前动画完成度（fraction）所对应的颜色值
       if (endHsv[0] - startHsv[0] > 180) {
           endHsv[0] -= 360;
       } else if (endHsv[0] - startHsv[0] < -180) {
           endHsv[0] += 360;
       }
       outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
       if (outHsv[0] > 360) {
           outHsv[0] -= 360;
       } else if (outHsv[0] < 0) {
           outHsv[0] += 360;
       }
       outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
       outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

       // 计算当前动画完成度（fraction）所对应的透明度
       int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);

       // 把 HSV 转换回 ARGB 返回
       return Color.HSVToColor(alpha, outHsv);
   }
}

ObjectAnimator animator = ObjectAnimator.ofInt(view, "color", 0xff00ff00);  
// 使用自定义的 HslEvaluator
animator.setEvaluator(new HsvEvaluator());  
animator.start(); 

```

## ofObject 

借助于TypeEvaluator,属性动画就可以通过 ofObject() 来对不限定类型的属性动画.方式很简单:

1. 为目标属性写一个自定义的TypeEvaluator

2. 使用ofObject()来创建Animator,并把自定义的TypeEvaluator 作为参数填入
PointFEvaluator具体代码:
```
public class PointFEvaluator implements TypeEvaluator<PointF> {
    PointF newPointF = new PointF();

    @Override
    public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
        float x = startValue.x + (fraction * (endValue.x - startValue.x));
        float y = startValue.y + (fraction * (endValue.y - startValue.y));
        newPointF.set(x, y);
        return newPointF;
    }

}

```
PonitView代码:
```
public class PointView extends View {

    private Paint paint;

    public PointView(Context context) {
        this(context, null);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();

    }

    private PointF position = new PointF();

    public PointF getPosition() {
        return position;
    }

    public void setPosition(PointF position) {
        this.position = position;

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(position.x * 800, position.y * 600, 50, paint);
    }
}

```
使用:

```
  ObjectAnimator animator = ObjectAnimator.ofObject(pv, "position", new PointFEvaluator(), new PointF(0, 0), new PointF(1, 1));
                animator.start();
```

## PropertyValuesHolder 同一个动画改变多个属性

对于ObjectAnimator,使用PropertyValueHolder来同时改变多个属性.

```
PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 1);  
PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 1);  
PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 1);

ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3)  
animator.start(); 

```

## AnimatorSet 多个动画配合执行
需要用到AnimatorSet
```
ObjectAnimator animator1 = ObjectAnimator.ofFloat(...);  
animator1.setInterpolator(new LinearInterpolator());  
ObjectAnimator animator2 = ObjectAnimator.ofInt(...);  
animator2.setInterpolator(new DecelerateInterpolator());

AnimatorSet animatorSet = new AnimatorSet();  
// 两个动画依次执行
animatorSet.playSequentially(animator1, animator2);  
animatorSet.start();  
```

## PropertyValuesHolders.ofKeyframe() 把通一个属性拆分

```
// 在 0% 处开始
Keyframe keyframe1 = Keyframe.ofFloat(0, 0);  
// 时间经过 50% 的时候，动画完成度 100%
Keyframe keyframe2 = Keyframe.ofFloat(0.5f, 100);  
// 时间见过 100% 的时候，动画完成度倒退到 80%，即反弹 20%
Keyframe keyframe3 = Keyframe.ofFloat(1, 80);  
PropertyValuesHolder holder = PropertyValuesHolder.ofKeyframe("progress", keyframe1, keyframe2, keyframe3);

ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder);  
animator.start();  

```
第二部分,[关于复杂的属性动画],就这三种:

1. 使用PropertyValueHolder 来对多个属性动画;

2. 使用 AnimatorSet 来同时管理调配多个动画

3. PropertyValusHolder 的进阶: 使用PropertyValuesHolder.ofKeyframe() 来把一个属性拆分成多段,执行更加精细的属性动画.


## ValueAnimator 最基本的轮子









