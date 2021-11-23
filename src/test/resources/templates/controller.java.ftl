package ${package.Controller};


import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xseven.c4p.common.constant.Constant;
import com.xseven.c4p.common.response.Result;
import com.xseven.c4p.common.response.ResultInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.Serializable;
import java.util.Collection;
import com.xseven.c4p.dto.${entity}DTO;
import com.xseven.c4p.entity.${entity};
import com.xseven.c4p.service.${table.serviceName};

<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequiredArgsConstructor
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if><#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
@Api(tags = "${table.comment!}管理")
<#if kotlin>
    class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
        public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
        public class ${table.controllerName} {
    </#if>

    private final static Logger logger = LoggerFactory.getLogger( ${table.controllerName}.class);
    final ${table.serviceName} service;
    //接口需要规范，请求路径不应该出现动词
    /**
    * 根据id获取对象
    * @param id 对象id
    * @return 对应信息或错误信息
    */
    @GetMapping("/{id}")
    @ApiOperation("根据id获取对象")
    public Result get${entity}ById(@PathVariable("id") Serializable id){
        try {
            ${entity} entity = service.getById(id);
            if (entity == null){
                return Result.error(ResultInfo.NOT_FOUND);
            }
            return Result.ok().data(entity);
        } catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * 分页获取所有对象
    * @param page 分页参数
    * @return 对象信息或错误信息
    */
    @GetMapping("/")
    @ApiOperation(value = "分页获取所有对象",notes = "start是起始位置，pageSize是每页显示条数")
    public Result get${entity}s(Page page){
        try {
            Page<${entity}> list = service.page(page);
            return Result.ok().data(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * 新增对象
    * @param entityDto 对象相关数据
    * @return 操作结果或错误信息
    */
    @PostMapping("/")
    @ApiOperation("新增对象")
    public Result save${entity}(@RequestBody ${entity}DTO entityDTO){
        try {
            boolean res = userService.save(entityDTO);
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * 更新对象所有信息
    * @param userDto 对象相关数据
    * @return 操作结果或错误信息
    */
    @PutMapping("/")
    @ApiOperation("更新对象所有信息")
    public Result update${entity}(@RequestBody ${entity}DTO entityDTO){
        if (entityDTO != null  && entityDTO.getId() != null){
            try {
                boolean res = service.updateById(entityDTO);
                return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
            }catch (Exception e) {
                logger.error(Constant.EXCEPTION_TITLE,e);
                return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
            }
        }
        return Result.error(ResultInfo.ERROR);
    }


    /**
    * 删除对象
    * @param id 对象ID
    * @return 操作结果或错误信息
    */
    @DeleteMapping("/{id}")
    @ApiOperation("删除对象")
    public Result delete${entity}(@PathVariable("id") Serializable id){
        try {
            boolean res = service.removeById(id);
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    /**
    * 批量删除对象
    * @param idList 对象ID
    * @return 操作结果或错误信息
    */
    @DeleteMapping("/batch")
    @ApiOperation("批量删除对象")
    public Result batchDelete${entity}(@RequestParam("idList[]") Collection<? extends Serializable> idList){
        try {
            boolean res = service.removeByIds(idList);
            return res? Result.ok(ResultInfo.SUCCESS): Result.error(ResultInfo.ERROR);
        }catch (Exception e) {
            logger.error(Constant.EXCEPTION_TITLE,e);
            return Result.error(ResultInfo.INTERNAL_SERVER_ERROR);
        }
    }

    // 自定义代码开始（请不要改写自动生成代码！！！）

    }
</#if>
