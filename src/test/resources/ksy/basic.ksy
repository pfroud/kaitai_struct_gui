meta:
  id: basic
seq:
  - id: sequential_bytes_scalar
    size: 2
  - id: sequential_bytes_list
    size: 2
    repeat: expr
    repeat-expr: 2
  - id: sequential_integer_scalar
    type: u1
  - id: sequential_integer_list
    type: u1
    repeat: expr
    repeat-expr: 2
  - id: sequential_float_scalar
    type: f4le
  - id: sequential_float_list
    type: f4le
    repeat: expr
    repeat-expr: 2
  - id: sequential_enum_scalar
    type: u1
    enum: my_enum
  - id: sequential_enum_list
    type: u1
    enum: my_enum
    repeat: expr
    repeat-expr: 2
  - id: sequential_boolean_scalar
    type: b1
  - id: sequential_boolean_list
    type: b1
    repeat: expr
    repeat-expr: 2
  - id: sequential_string_scalar
    type: str
    encoding: UTF-8
    size: 2
  - id: sequential_string_list
    type: str
    encoding: UTF-8
    size: 2
    repeat: expr
    repeat-expr: 2
  - id: sequential_struct_scalar
    type: my_type
  - id: sequential_struct_list
    type: my_type
    repeat: expr
    repeat-expr: 2
instances:
  # The 'chunk_instance' name means it occupies a region in the binary file.
  # It will have type ru.mingun.kaitai.struct.tree.ChunkNode.
  chunk_instance_bytes_scalar:
    size: 2
  chunk_instance_bytes_list:
    size: 2
    repeat: expr
    repeat-expr: 2
  chunk_instance_integer_scalar:
    type: u1
  chunk_instance_integer_list:
    type: u1
    repeat: expr
    repeat-expr: 2
  chunk_instance_float_scalar:
    type: f4le
  chunk_instance_float_list:
    type: f4le
    repeat: expr
    repeat-expr: 2
  chunk_instance_enum_scalar:
    type: u1
    enum: my_enum
  chunk_instance_enum_list:
    type: u1
    enum: my_enum
    repeat: expr
    repeat-expr: 2
  chunk_instance_boolean_scalar:
    type: b1
  chunk_instance_boolean_list:
    type: b1
    repeat: expr
    repeat-expr: 2
  chunk_instance_string_scalar:
    type: str
    encoding: UTF-8
    size: 2
  chunk_instance_string_list:
    type: str
    encoding: UTF-8
    size: 2
    repeat: expr
    repeat-expr: 2
  chunk_instance_struct_scalar:
    type: my_type
  chunk_instance_struct_list:
    type: my_type
    repeat: expr
    repeat-expr: 2
  ############################################
  value_instance_bytes_scalar:
    value: "[0,1]"
  value_instance_bytes_list:
    value: "[[0,1], [2,3]]"
  value_instance_integer_scalar:
    value: 1
  value_instance_integer_list:
    # use numbers greater than 255 otherwise it will be byte[]
    value: "[300, 301]"
  value_instance_float_scalar:
    value: 0.1
  value_instance_float_list:
    value: "[0.1, 0.2]"
  value_instance_enum_scalar:
    value: my_enum::one
  value_instance_enum_list:
    value: "[my_enum::one, my_enum::two]"
  value_instance_boolean_scalar:
    value: true
  value_instance_boolean_list:
    value: "[true, false]"
  value_instance_string_scalar:
    value: "'hello'"
  value_instance_string_list:
    value: "['hello', 'world']"
  value_instance_struct_scalar:
    value: sequential_struct_scalar
  value_instance_struct_list:
    value: "[sequential_struct_scalar, sequential_struct_scalar]"
enums:
  my_enum:
    1: one
    2: two
types:
  my_type:
    seq:
      [ ]
