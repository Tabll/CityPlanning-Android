#include <jni.h>
#include <string>

extern "C"
jstring
Java_com_wts_sxgh_sxgh_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "请选择既定住宅区";
    return env->NewStringUTF(hello.c_str());
}
