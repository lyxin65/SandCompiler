# The Structure of the Program Representation 

### Abstract Syntax Tree
    ASTNode
        Program
        Definition
            FuncDef
            ClassDef
            VarDef
        TypeNode
            ArrayTypeNode
            BasicTypeNode
            ClassTypeNode
        Stmt
            ForStmt
            WhileStmt
            IfStmt
            BreakStmt
            ReturnStmt
            BlockStmt
            VarDeclStmt
            ExprStmt
            EmptyStmt
        Expr
            IntLiteral
            BoolLiteral
            StringLiteral
            NullLiteral
            ID
            ArrayExpr
            FuncCallExpr
            NewExpr
            PrefixExpr
            SuffixExpr
            MemberExpr
            BinaryExpr
            TernaryExpr
            AssignExpr
            
### Symbol Table Structure
    ClassSymbol
    BasicSymbol
    FuncSymbol
    VarSymbol
    VarType
        BasicType
        ClassType
        ArrayType
    SymbolTable
