package ${package.Controller};


import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Entity}.${entity};
import ${package.Entity}.vo.${entity}VO;
import ${package.Service}.${table.serviceName};
import net.suncaper.oasis.binding.dto.Pagination;
import net.suncaper.oasis.binding.vo.JsonResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;

<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
import net.suncaper.oasis.binding.controller.BaseCrudRestController;
<#else>
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api<#if package.ModuleName??>/${package.ModuleName}</#if><#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass}<${entity}, ${table.serviceName}> {
<#else>
public class ${table.controllerName} {
</#if>

    /***
     * 查询ViewObject的分页数据
     * <p>
     * url请求参数示例: /list?field=abc&pageSize=20&pageIndex=1&orderBy=id
     * </p>
     * @return
     * @throws Exception
     */
    @GetMapping("/list")
    public JsonResult getViewObjectListWithMapping(${entity} entity, Pagination pagination) throws Exception {
        return super.getViewObjectList(entity, pagination, ${entity}VO.class);
    }

    /***
     * 根据资源id查询ViewObject
     * @param id ID
     * @return
     * @throws Exception
     */
    @GetMapping("/{id}")
    public JsonResult getViewObjectWithMapping(@PathVariable("id") Serializable id) throws Exception {
        return super.getViewObject(id, ${entity}VO.class);
    }

    /***
     * 创建资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @PostMapping("/")
    public JsonResult createEntityWithMapping(@Valid @RequestBody ${entity} entity) throws Exception {
        return super.createEntity(entity);
    }

    /***
     * 根据ID更新资源对象
     * @param entity
     * @return JsonResult
     * @throws Exception
     */
    @PutMapping("/{id}")
    public JsonResult updateEntityWithMapping(@PathVariable("id") Serializable id, @Valid @RequestBody ${entity} entity) throws Exception {
        return super.updateEntity(id, entity);
    }

    /***
     * 根据id删除资源对象
     * @param id
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}")
    public JsonResult deleteEntityWithMapping(@PathVariable("id") Serializable id) throws Exception {
        return super.deleteEntity(id);
    }

    /***
     * 根据ids批量删除资源对象
     * @param ids
     * @return
     * @throws Exception
     */
    @DeleteMapping("/batch")
    public JsonResult batchDeleteEntitiesWithMapping(@RequestParam("ids[]") Collection<? extends Serializable> ids) throws Exception {
        return super.batchDeleteEntities(ids);
    }

    // 自定义代码开始（请不要改写自动生成代码！！！）

}
</#if>
