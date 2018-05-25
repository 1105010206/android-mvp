package com.android.mvp.demo.utils.network;

import android.content.Context;
import android.util.Log;

import com.android.mvp.demo.R;
import com.android.mvp.demo.utils.ToastUtil;
import com.android.mvp.demo.utils.exception.ApiException;
import com.android.mvp.demo.utils.exception.ResultException;
import com.google.gson.JsonParseException;
import com.google.protobuf.TextFormat;

import org.json.JSONException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;

/**
 * 作者: 刘康
 * 时间: 2017/12/8 0008 16:54
 */

public abstract class GalbsNetObersever<T> implements Observer<T> {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    //出错提示
    private Context context;
    private String networkMsg;
    private String parseMsg;
    private String unknownMsg;

    protected GalbsNetObersever(Context mContext) {
        this.context = mContext;
        networkMsg = context.getString(R.string.network_exception);
        parseMsg = context.getString(R.string.parse_net_info_exception);
        unknownMsg = context.getString(R.string.unknow_exception);
    }


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        Log.d("release_test", "发生了异常");
        Throwable throwable = e;

        // 联网状态
        NetworkManager netManager = new NetworkManager(context);
        if (!netManager.isMobileConnected() && !netManager.isWifiConnected()) {
            ToastUtil.showShortToast(networkMsg);//onError();
            return;
        }

        //获取最根源的异常
        while (throwable.getCause() != null) {
            e = throwable;
            throwable = throwable.getCause();
        }

        ApiException ex;
        if (e instanceof HttpException) {            //HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, httpException.code());
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                    onPermissionError(ex);          //权限错误，需要实现
                    break;
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.setDisplayMessage(networkMsg); //均视为网络错误
                    onError(ex);
                    break;
            }
        } else if (e instanceof ResultException) {   //服务器返回的错误
            ResultException resultException = (ResultException) e;
            ex = new ApiException(resultException, resultException.getErrCode());
            onResultError(ex);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof TextFormat.ParseException) {
            ex = new ApiException(e, ApiException.PARSE_ERROR);
            ex.setDisplayMessage(parseMsg);           //均视为解析错误
            onError(ex);
        } else {
            ex = new ApiException(e, ApiException.UNKNOWN);
            ex.setDisplayMessage(unknownMsg);        //未知错误
            onError(ex);
        }
    }


    /**
     * 错误回调
     */
    protected abstract void onError(ApiException ex);

    /**
     * 权限错误，需要实现重新登录操作
     */
    protected void onPermissionError(ApiException ex) {
        ToastUtil.showShortToast(context.getResources().getString(R.string.need_net_perssion_exception));
    }

    ;

    /**
     * 服务器返回的错误
     */
    protected void onResultError(ApiException ex) {
    }

    ;

    @Override
    public void onCompleted() {

    }

    // 响应编码	响应编码描述
    protected void toastErrorInfo(int state) {
        switch (state) {
            case 1: //下行成功
                break;
            case -999: //系统异常
                break;
            case -1: //协议头长度异常
                break;
            case -2: //协议类型异常
                break;
            case -3: //请求类型异常
                break;
            case -4: //终端类型异常
                break;
            case -11: //非法用户，令牌错误
                break;
            case -12: //非法用户，厂商项目不存在
                break;
            case -15: //数据异常：协议头
                break;
            case -16: //终端未绑定被监护端
                break;
            case -17: //非法用户
                break;
            case -18: //参数错误
                break;
            case -19: //等级权限不够
                break;
            case -20: //非法操作
                break;
            case -21: //协议体解析失败
                break;
            case -22: //APP未授权，错误的授权key
                break;
            case -24: //非法的IMEI，不允许访问接口
                break;
            case -1000: //请求类型不存在
                break;
            case -1001: //文件不存在
                break;
            case -1002: //文件格式有误
                break;
            case -1003: //文件大小超过限制
                break;
            case -1004: //文件重复
                break;
            case -1005: //文件流水号无效
                break;
            case -1006: //上传文件异常
                break;
            case -1007: //参数错误
                break;
            case -100000: //协议请求类型无效：用户管理
                break;
            case -1010101: //协议长度无效：用户登录。
                break;
            case -1010102: //用户名或密码错误
                break;
            case -1010103: //用户登录异常
                break;
            case -1010104: //用户名不唯一
                break;
            case -1010105: //不能绑定旧版的终端
                break;
            case -1010201: //协议长度无效：解除绑定关系/删除被监护人
                break;
            case -1010202: //解除关系失败：解除绑定关系/删除被监护人
                break;
            case -1030101: //参数错误：激活
                break;
            case -1030102: //激活失败：激活
                break;
            case -1030103: //邮箱格式有误：激活
                break;
            case -1030104: //邮箱已经存在：激活
                break;
            case -1040101: //参数错误：找回密码
                break;
            case -1040102: //未设置邮箱：找回密码
                break;
            case -1040103: //非法的找回密码用户：找回密码
                break;
            case -1040105: //发送邮件失败：找回密码
                break;
            case -1040203: //修改密码失败
                break;
            case -1040204: //非法的被监护人:修改密码
                break;
            case -1040205: //新密码格式错误:修改密码
                break;
            case -1040206: //旧密码错误:修改密码
                break;
            case -100301: //协议长度无效：修改昵称
                break;
            case -100302: //修改昵称失败
                break;
            case -100303: //参数异常：修改昵称
                break;
            case -100401: //协议长度无效：修改头像
                break;
            case -100402: //修改头像失败
                break;
            case -100403: //参数异常：修改头像
                break;
            case -1070101: //被监护人列表为空
                break;
            case -1080101: //不合法的用户：在线状态查询
                break;
            case -1090101: //监护人已经存在：添加监护人
                break;
            case -1090201: //监护人号码不能为空：删除/清空监护人
                break;
            case -1090301: //无有效的监护人：查询监护人
                break;
            case -200000: //协议请求类型无效：轨迹请求
                break;
            case -200101: //协议长度无效：查询轨迹
                break;
            case -2020204: //被监护人配置不存在：设置实时轨迹
                break;
            case -2020205: //密码验证不通过：设置实时轨迹
                break;
            case -200102: //参数异常
                break;
            case -2030105: //围栏名称已存在:创建围栏
                break;
            case -2030205: //围栏名称已存在:编辑围栏
                break;
            case -2030206: //围栏不存在:编辑围栏
                break;
            case -2030305: //围栏不存在:删除围栏
                break;
            case -2030404: //接受人不存在:围栏报警
                break;
            case -2080103: //定位参数不足:位置共享
                break;
            case -2080404: //接受人不存在:位置共享
                break;
            case -3000000: //协议请求类型无效：系统管理
                break;
            case -3010101: //协议长度无效: 同步错误日志
                break;
            case -3010102: //协议长度无效: 同步错误日志，日志列表为空
                break;
            case -6060102: //协议长度无效: 警报接收方式设置
                break;
            case -6060103: //参数异常: 警报接收方式设置
                break;
            case -3020101: //协议长度无效: 警报接收方式设置
                break;
            case -3020102: //获取失败: 警报接收方式设置
                break;
            case -6010302: //协议长度无效: 切换电源模式
                break;
            case -6010303: //参数异常: 切换电源模式
                break;
            case -6010202: //协议长度无效: 查询电源模式配置
                break;
            case -6010203: //参数异常: 查询电源模式配置
                break;
            case -6050102: //协议长度无效: 开启关闭时间同步
                break;
            case -6050103: //参数异常: 开启关闭时间同步
                break;
            case -400001: //操作失败：关爱
                break;
            case -400000: //协议请求类型无效：关爱
                break;
            case -4020202: //协议长度无效：开启关闭防脱落报警
                break;
            case -4020203: //参数异常：开启关闭防脱落报警
                break;
            case -4020302: //协议长度无效：开启关闭防脱落报警
                break;
            case -4020303: //参数异常：查询防脱落报警开关设置
                break;
            case -4030102: //协议长度无效：开启关闭低电量报警
                break;
            case -4030103: //参数异常：开启关闭低电量报警
                break;
            case -4030302: //协议长度无效：查询低电量报警开关设置
                break;
            case -4030303: //参数异常：查询低电量报警开关设置
                break;
            case -4040102: //协议长度无效：开启关闭防骚扰报警
                break;
            case -4040103: //参数异常：开启关闭防骚扰报警
                break;
            case -4040104: //指定的防骚扰分组不存在：开启关闭防骚扰报警
                break;
            case -4040202: //协议长度无效：设置防骚扰分组
                break;
            case -4040203: //参数异常：设置防骚扰分组
                break;
            case -4040204: //指定的防骚扰分组不存在：设置防骚扰分组
                break;
            case -4040205: //新的防骚扰分组名称已经存在：设置防骚扰分组
                break;
            case -4040206: //防骚扰分组已超过上限：设置防骚扰分组
                break;
            case -4040302: //协议长度无效：设置防骚扰联系人
                break;
            case -4040303: //参数异常：设置防骚扰联系人
                break;
            case -4040304: //防骚扰联系人不存在指定的分组：设置防骚扰联系人
                break;
            case -4040305: //防骚扰联系人已经存在：设置防骚扰联系人
                break;
            case -4040306: //防骚扰联系人已超过上限：设置防骚扰联系人
                break;
            case -4040307: //防骚扰联系人指定的分组中不存在手机号：设置防骚扰联系人
                break;
            case -4040403: //参数异常：获取防骚扰联系人列表
                break;
            case -4050102: //协议长度无效：关闭日程
                break;
            case -4050103: //参数异常：关闭日程
                break;
            case -4050104: //指定日程不存在：关闭日程
                break;
            case -4050202: //协议长度无效：开启日程
                break;
            case -4050203: //参数异常：开启日程
                break;
            case -4050204: //指定日程不存在：开启日程
                break;
            case -4050205: //指定日程已存在：开启日程
                break;
            case -4050303: //参数异常：查询日程列表
                break;
            case -500000: //协议请求类型无效：健康
                break;
            case -5010102: //协议长度无效：开启关闭运动记步
                break;
            case -5010103: //参数异常：开启关闭运动记步
                break;
            case -5010104: //不存在运动记步相关设置：开启关闭运动记步
                break;
            case -5010201: //失败：上传运动记步数据
                break;
            case -5010203: //参数异常：上传运动记步数据
                break;
            case -5010302: //协议长度无效：查询运动记步数据
                break;
            case -5010303: //参数异常：查询运动记步数据
                break;
            case -5010403: //参数异常：查询运动记步设置
                break;
            case -5020102: //协议长度无效：开启关闭血压
                break;
            case -5020103: //参数异常：开启关闭血压
                break;
            case -5020104: //不存在血压相关设置：开启关闭血压
                break;
            case -5020201: //失败：上传血压数据
                break;
            case -5020203: //参数异常：上传血压数据
                break;
            case -5020302: //协议长度无效：查询血压数据
                break;
            case -5020303: //参数异常：查询血压数据
                break;
            case -5020403: //参数异常：查询血压设置
                break;
            case -5030102: //协议长度无效：开启关闭体温
                break;
            case -5030103: //参数异常：开启关闭体温
                break;
            case -5030104: //不存在体温相关设置：开启关闭体温
                break;
            case -5030201: //失败：上传体温数据
                break;
            case -5030203: //参数异常：上传体温数据
                break;
            case -5030302: //协议长度无效：查询体温数据
                break;
            case -5030303: //参数异常：查询体温数据
                break;
            case -5030403: //参数异常：查询体温设置
                break;
            default:

                break;
        }
    }


}