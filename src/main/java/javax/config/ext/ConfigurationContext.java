///*
// * CREDIT SUISSE IS WILLING TO LICENSE THIS SPECIFICATION TO YOU ONLY UPON THE CONDITION THAT YOU
// * ACCEPT ALL OF THE TERMS CONTAINED IN THIS AGREEMENT. PLEASE READ THE TERMS AND CONDITIONS OF THIS
// * AGREEMENT CAREFULLY. BY DOWNLOADING THIS SPECIFICATION, YOU ACCEPT THE TERMS AND CONDITIONS OF
// * THE AGREEMENT. IF YOU ARE NOT WILLING TO BE BOUND BY IT, SELECT THE "DECLINE" BUTTON AT THE
// * BOTTOM OF THIS PAGE. Specification: JSR-xxx Java Configuration API ("Specification") Copyright
// * (c) 2012-2013, Credit Suisse All rights reserved.
// */
//package javax.config.ext;
//
//import javax.config.Stage;
//import java.util.Map;
//import java.util.Objects;
//
///**
// * Models the current runtime environment. Instances of this class are used to
// * evaluate the correct configuration artifacts.<br/>
// * <h3>Implementation PropertyMapSpec</h3>
// * <p>
// * Implementations of this interface must be
// * <ul>
// * <li>Thread safe.
// * <li>Immutable
// * <li>serializable
// * </ul>
// *
// * @author Anatole Tresch
// */
//public final class ConfigurationContext extends AbstractContext{
//
//    private static final String KEY_NAME = "name";
//    private static final long serialVersionUID = 904363460955115875L;
//
//    private ConfigurationContext(Builder builder){
//        super(builder);
//    }
//
//	/**
//	 * Get the name of the environment. The environment's name must be unique in
//	 * combination with the EnvironmentType.
//	 *
//	 * @return the environment's name, not {@code null}
//	 */
//	public String getName(){
//        return getText(KEY_NAME);
//    }
//
//    /**
//     * Get the environment's stage.
//     * @return the current stage, never null.
//     */
//    public Stage getStage(){
//        return getAttribute(Stage.class);
//    }
//
//    public static final class Builder extends AbstractBuilder<Builder>{
//
//        public Builder(String name){
//            Objects.requireNonNull(name);
//            if(name.isEmpty()){
//                throw new IllegalArgumentException("Name is empty.");
//            }
//            super.setText(KEY_NAME, name);
//            setStage(Stage.Development);
//        }
//
//        public Builder(ConfigurationContext context){
//            Objects.requireNonNull(context);
//            setAll(context);
//        }
//
//        public Builder setStage(Stage stage){
//            setObject(stage);
//            return this;
//        }
//
//        public Builder setContextWithOverride(ConfigurationContext context){
//            super.setAll(context);
//            return this;
//        }
//
//        public Builder setContextIgnoreDuplicates(ConfigurationContext context){
//            for(Map.Entry<Class<?>,Map<Object,Object>> en: context.attributes.entrySet()){
//                Map<Object,Object> values = this.attributes.get(en.getKey());
//                if(values!=null){
//                    for(Map.Entry<Object,Object> valEn: en.getValue().entrySet()){
//                        values.putIfAbsent(en.getKey(), en.getValue());
//                    }
//                }
//            }
//            return this;
//        }
//
//        @Override
//        public ConfigurationContext build(){
//            return new ConfigurationContext(this);
//        }
//    }
//
//}
