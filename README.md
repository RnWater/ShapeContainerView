# ShapeContainerView
initFirst

这只是个demo样式。
作用：1：减少drawable样式的创建缩减apk体积
     2：使用灵活方便可以创建大多数场景需要的背景
     3：xml展示所见即所得
     
下面介绍其使用方式

<ShapeTextView
 *         android:id="@+id/shape_text"
 *         android:layout_width="match_parent"
 *         android:layout_height="wrap_content"
 *         app:shape_openSelector="true"
 *         android:text="ShapeText"
 *         android:padding="10dp"
 *         android:layout_margin="16dp"
 *         android:gravity="center"
 *         app:shape_solidColor="@color/theme_ori"
 *         app:shape_strokeColor="@color/theme_color"
 *         app:shape_solidTouchColor="@color/theme_color"
 *         app:shape_strokeTouchColor="@color/black"
 *         app:shape_textTouchColor="@color/white"
 *         app:shape_textColor="@color/blue"
 *         app:shape_dashWidth="6dp"
 *         app:shape_dashGap="6dp"
 *         app:shape_strokeWidth="3dp"
 *         app:shape_radius="30dp"
 *         />
 
 <com.rnwater.shape.group.ConstraintLayoutShape
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:shape_openSelector="true"
    app:shape_solidColor="#00f"
    app:shape_strokeColor="#0f0"
    app:shape_strokeWidth="5dp"
    app:shape_radius="24dp"
    android:layout_margin="20dp"
    tools:context=".MainActivity">

    <com.rnwater.shape.view.ShapeTextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:shape_gradient="TOP_BOTTOM"
        android:text="就看见就看见"
        app:shape_solidColor="#f00"
        app:shape_openSelector="true"
        app:shape_textColor="#000"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

</com.rnwater.shape.group.ConstraintLayoutShape>


 如果需要做点击显示不同字体颜色和不同背景颜色的话在代码中调用
 
 调用的地方 ShapeTextView.setSelected(true)
