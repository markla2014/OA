package cn.itcast.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

public abstract class ModelDriverBaseAction<T> extends BaseAction  implements ModelDriven<T>{

	protected T model;

	@SuppressWarnings("unchecked")
	public ModelDriverBaseAction() {
		try {
			// 得到model的类型信息
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class clazz = (Class) pt.getActualTypeArguments()[0];

			// 通过反射生成model的实例
			model = (T) clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
}
