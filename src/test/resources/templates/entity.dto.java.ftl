package ${package.Parent}.dto;


import ${package.Entity}.${entity};

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ${entity}DTO extends ${entity} {
}
