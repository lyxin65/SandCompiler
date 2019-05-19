





default rel

global __hasValue
global __getValue
global __setValue
global __print
global __println
global __getString
global __getInt
global __toString
global __string_length
global __string_substring
global __string_parseInt
global __string_ord
global __stringConcate
global __stringCompare
global __value
global __has
global __real_addr
global main

extern strcmp
extern __sprintf_chk
extern memcpy
extern malloc
extern __isoc99_scanf
extern puts
extern __printf_chk


SECTION .text   6

__hasValue:
        mov     rax, rdi
        mov     rdx, qword 1323E34A2B10BF67H
        imul    rdx
        mov     rax, rdi
        sar     rax, 63
        sar     rdx, 3
        sub     rdx, rax
        mov     eax, 107
        imul    rdx, rax
        mov     rax, rdi
        sub     rax, rdx
        mov     rdx, rax
        xor     eax, eax

        cmp     qword [abs __real_addr+rdx*8], rdi
        jz      L_002
L_001:

        ret





ALIGN   8
L_002:  cmp     rsi, 209
        ja      L_001
        imul    rdx, rdx, 210
        add     rsi, rdx

        mov     rax, qword [abs __has+rsi*8]
        ret






ALIGN   8

__getValue:
        mov     rax, rdi
        mov     rdx, qword 1323E34A2B10BF67H
        imul    rdx
        mov     rax, rdi
        sar     rax, 63
        sar     rdx, 3
        sub     rdx, rax
        mov     eax, 107
        imul    rdx, rax
        sub     rdi, rdx
        imul    rdi, rdi, 210
        add     rsi, rdi

        mov     rax, qword [abs __value+rsi*8]
        ret






ALIGN   8

__setValue:
        cmp     rsi, 209
        mov     r9, rdx
        ja      L_008
        mov     r8, qword 1323E34A2B10BF67H
        mov     rax, rdi
        mov     r10d, 107
        imul    r8
        mov     rax, rdi
        sar     rax, 63
        mov     r8, rdx
        sar     r8, 3
        sub     r8, rax
        mov     rax, rdi
        imul    r8, r10
        sub     rax, r8
        mov     r8, rax

        mov     rax, qword [abs __real_addr+rax*8]
        cmp     rax, rdi
        jz      L_007
        test    rax, rax
        je      L_012
        imul    rax, r8, 1680
        mov     edx, 1680

        lea     rdi, [abs __has+rax]
        test    dil, 01H
        jne     L_013
L_003:  test    dil, 02H
        jne     L_014
L_004:  test    dil, 04H
        jne     L_015
L_005:  mov     ecx, edx
        xor     eax, eax
        shr     ecx, 3
        test    dl, 04H
        rep stosq
        jnz     L_011
        test    dl, 02H
        jnz     L_010
L_006:  and     edx, 01H
        jnz     L_009




ALIGN   8
L_007:  mov     rax, r9
        imul    r8, r8, 210
        add     rsi, r8

        mov     qword [abs __has+rsi*8], 1

        mov     qword [abs __value+rsi*8], r9
        ret





ALIGN   8
L_008:

        ret





ALIGN   8
L_009:  mov     byte [rdi], 0
        jmp     L_007





ALIGN   8
L_010:  xor     eax, eax
        add     rdi, 2
        mov     word [rdi-2H], ax
        and     edx, 01H
        jz      L_007
        jmp     L_009





ALIGN   8
L_011:  mov     dword [rdi], 0
        add     rdi, 4
        test    dl, 02H
        jz      L_006
        jmp     L_010





ALIGN   8
L_012:

        mov     qword [abs __real_addr+r8*8], rdi
        jmp     L_007





ALIGN   8
L_013:

        mov     byte [abs __has+rax], 0
        add     rdi, 1
        mov     dl, -113
        jmp     L_003





ALIGN   8
L_014:  xor     ecx, ecx
        add     rdi, 2
        sub     edx, 2
        mov     word [rdi-2H], cx
        jmp     L_004





ALIGN   8
L_015:  mov     dword [rdi], 0
        sub     edx, 4
        add     rdi, 4
        jmp     L_005







ALIGN   16

__print:
        lea     rdx, [rdi+8H]
        mov     esi, L_032
        mov     edi, 1
        xor     eax, eax
        jmp     __printf_chk







ALIGN   16

__println:
        add     rdi, 8
        jmp     puts






ALIGN   8

__getString:
        push    rbp
        mov     esi, __buffer.3442
        mov     edi, L_032
        xor     eax, eax
        push    rbx
        mov     ebx, __buffer.3442
        sub     rsp, 8
        call    __isoc99_scanf
L_016:  mov     edx, dword [rbx]
        add     rbx, 4
        lea     eax, [rdx-1010101H]
        not     edx
        and     eax, edx
        and     eax, 80808080H
        jz      L_016
        mov     edx, eax
        shr     edx, 16
        test    eax, 8080H
        cmove   eax, edx
        lea     rdx, [rbx+2H]
        cmove   rbx, rdx
        add     al, al
        sbb     rbx, 3
        sub     rbx, __buffer.3442
        lea     edi, [rbx+8H]
        movsxd  rdi, edi
        call    malloc
        mov     rbp, rax
        lea     rdx, [rbx+1H]
        movsxd  rax, ebx
        lea     rdi, [rbp+8H]
        mov     qword [rbp], rax
        mov     esi, __buffer.3442
        call    memcpy
        add     rsp, 8
        mov     rax, rbp
        pop     rbx
        pop     rbp
        ret







ALIGN   16

__getInt:
        sub     rsp, 24
        mov     edi, L_033
        xor     eax, eax
        lea     rsi, [rsp+8H]
        call    __isoc99_scanf
        mov     rax, qword [rsp+8H]
        add     rsp, 24
        ret


        nop

ALIGN   16
__toString:
        push    rbp
        mov     rbp, rdi
        mov     edi, 32
        push    rbx
        sub     rsp, 8
        call    malloc
        lea     rdi, [rax+8H]
        mov     rbx, rax
        mov     r8, rbp
        mov     ecx, L_033
        mov     edx, 24
        mov     esi, 1
        xor     eax, eax
        call    __sprintf_chk
        cdqe
        mov     qword [rbx], rax
        add     rsp, 8
        mov     rax, rbx
        pop     rbx
        pop     rbp
        ret







ALIGN   16

__string_length:
        mov     rax, qword [rdi]
        ret







ALIGN   16

__string_substring:
        push    r15
        push    r14
        push    r13
        movsxd  r13, esi
        push    r12
        mov     r12d, edx
        sub     r12d, r13d
        push    rbp
        mov     rbp, rdi
        lea     edi, [r12+0AH]
        push    rbx
        lea     ebx, [r12+1H]
        movsxd  rdi, edi
        sub     rsp, 8
        call    malloc
        movsxd  rdx, ebx
        test    ebx, ebx
        mov     qword [rax], rdx
        jle     L_018
        lea     edi, [r13+8H]
        add     r13, rbp
        lea     rcx, [rax+18H]
        lea     rdx, [r13+8H]
        lea     rsi, [r13+18H]
        cmp     rcx, rdx
        lea     rdx, [rax+8H]
        setbe   cl
        cmp     rsi, rdx
        setbe   dl
        or      cl, dl
        je      L_019
        cmp     ebx, 15
        jbe     L_019
        mov     esi, ebx
        xor     edx, edx
        xor     r8d, r8d
        shr     esi, 4
        mov     ecx, esi
        shl     ecx, 4
L_017:  movdqu  xmm0, oword [r13+rdx+8H]
        add     r8d, 1
        movdqu  oword [rax+rdx+8H], xmm0
        add     rdx, 16
        cmp     r8d, esi
        jc      L_017
        cmp     ebx, ecx
        je      L_018
        lea     edx, [rdi+rcx]
        lea     r13d, [rcx+8H]
        movsxd  rdx, edx
        movzx   esi, byte [rbp+rdx]
        movsxd  rdx, r13d
        mov     byte [rax+rdx], sil
        lea     edx, [rcx+1H]
        cmp     ebx, edx
        jle     L_018
        add     edx, edi
        lea     r11d, [rcx+9H]
        movsxd  rdx, edx
        movzx   esi, byte [rbp+rdx]
        movsxd  rdx, r11d
        mov     byte [rax+rdx], sil
        lea     edx, [rcx+2H]
        cmp     ebx, edx
        jle     L_018
        add     edx, edi
        lea     r10d, [rcx+0AH]
        movsxd  rdx, edx
        movzx   esi, byte [rbp+rdx]
        movsxd  rdx, r10d
        mov     byte [rax+rdx], sil
        lea     edx, [rcx+3H]
        cmp     ebx, edx
        jle     L_018
        add     edx, edi
        lea     r9d, [rcx+0BH]
        movsxd  rdx, edx
        movzx   esi, byte [rbp+rdx]
        movsxd  rdx, r9d
        mov     byte [rax+rdx], sil
        lea     edx, [rcx+4H]
        cmp     ebx, edx
        jle     L_018
        add     edx, edi
        lea     r8d, [rcx+0CH]
        movsxd  rdx, edx
        movzx   esi, byte [rbp+rdx]
        movsxd  rdx, r8d
        mov     byte [rax+rdx], sil
        lea     edx, [rcx+5H]
        cmp     ebx, edx
        jle     L_018
        add     edx, edi
        lea     esi, [rcx+0DH]
        movsxd  rdx, edx
        movzx   r14d, byte [rbp+rdx]
        movsxd  rdx, esi
        mov     byte [rax+rdx], r14b
        lea     edx, [rcx+6H]
        cmp     ebx, edx
        jle     L_018
        add     edx, edi
        movsxd  rdx, edx
        movzx   r15d, byte [rbp+rdx]
        lea     edx, [rcx+0EH]
        movsxd  r14, edx
        mov     byte [rax+r14], r15b
        lea     r14d, [rcx+7H]
        cmp     ebx, r14d
        jle     L_018
        add     r14d, edi
        cmp     ebx, r13d
        movsxd  r14, r14d
        movzx   r15d, byte [rbp+r14]
        lea     r14d, [rcx+0FH]
        movsxd  r14, r14d
        mov     byte [rax+r14], r15b
        jle     L_018
        add     r13d, edi
        cmp     ebx, r11d
        movsxd  r13, r13d
        movzx   r14d, byte [rbp+r13]
        lea     r13d, [rcx+10H]
        movsxd  r13, r13d
        mov     byte [rax+r13], r14b
        jle     L_018
        add     r11d, edi
        cmp     ebx, r10d
        movsxd  r11, r11d
        movzx   r13d, byte [rbp+r11]
        lea     r11d, [rcx+11H]
        movsxd  r11, r11d
        mov     byte [rax+r11], r13b
        jle     L_018
        add     r10d, edi
        cmp     ebx, r9d
        movsxd  r10, r10d
        movzx   r11d, byte [rbp+r10]
        lea     r10d, [rcx+12H]
        movsxd  r10, r10d
        mov     byte [rax+r10], r11b
        jle     L_018
        add     r9d, edi
        cmp     ebx, r8d
        movsxd  r9, r9d
        movzx   r10d, byte [rbp+r9]
        lea     r9d, [rcx+13H]
        movsxd  r9, r9d
        mov     byte [rax+r9], r10b
        jle     L_018
        add     r8d, edi
        cmp     ebx, esi
        movsxd  r8, r8d
        movzx   r9d, byte [rbp+r8]
        lea     r8d, [rcx+14H]
        movsxd  r8, r8d
        mov     byte [rax+r8], r9b
        jle     L_018
        add     esi, edi
        cmp     ebx, edx
        movsxd  rsi, esi
        movzx   r8d, byte [rbp+rsi]
        lea     esi, [rcx+15H]
        movsxd  rsi, esi
        mov     byte [rax+rsi], r8b
        jle     L_018
        add     edx, edi
        add     ecx, 22
        movsxd  rdx, edx
        movsxd  rcx, ecx
        movzx   edx, byte [rbp+rdx]
        mov     byte [rax+rcx], dl
L_018:  add     r12d, 9
        movsxd  r12, r12d
        mov     byte [rax+r12], 0
        add     rsp, 8
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret





ALIGN   16
L_019:  movsxd  rdi, edi
        xor     ecx, ecx
        add     rbp, rdi




ALIGN   16
L_020:  movzx   edx, byte [rbp+rcx]
        mov     byte [rax+rcx+8H], dl
        add     rcx, 1
        cmp     ebx, ecx
        jg      L_020
        jmp     L_018







ALIGN   16

__string_parseInt:
        movsx   edx, byte [rdi+8H]
        cmp     dl, 45
        jz      L_024
        lea     rcx, [rdi+8H]
        xor     edi, edi
L_021:  lea     eax, [rdx-30H]
        cmp     al, 9
        mov     eax, 0
        ja      L_023




ALIGN   8
L_022:  sub     edx, 48
        lea     rax, [rax+rax*4]
        add     rcx, 1
        movsxd  rdx, edx
        lea     rax, [rdx+rax*2]
        movsx   edx, byte [rcx]
        lea     esi, [rdx-30H]
        cmp     sil, 9
        jbe     L_022
L_023:  mov     rdx, rax
        neg     rdx
        test    edi, edi
        cmovne  rax, rdx
        ret





ALIGN   8
L_024:  lea     rcx, [rdi+9H]
        movsx   edx, byte [rdi+9H]
        mov     edi, 1
        jmp     L_021


        nop

ALIGN   16
__string_ord:
        movsx   rax, byte [rdi+rsi+8H]
        ret







ALIGN   16

__stringConcate:
        push    r15
        push    r14
        push    r13
        push    r12
        mov     r12, rsi
        push    rbp
        mov     rbp, rdi
        push    rbx
        sub     rsp, 8
        mov     rbx, qword [rdi]
        mov     r13, qword [rsi]
        lea     r14, [rbx+r13]
        lea     rdi, [r14+9H]
        call    malloc
        test    rbx, rbx
        mov     qword [rax], r14
        jle     L_026
        lea     rcx, [rbp+8H]
        lea     rdx, [rax+18H]
        lea     rsi, [rbp+18H]
        cmp     rcx, rdx
        lea     rdx, [rax+8H]
        setae   cl
        cmp     rsi, rdx
        setbe   dl
        or      cl, dl
        je      L_029
        cmp     rbx, 15
        jbe     L_029
        mov     rsi, rbx
        xor     ecx, ecx
        xor     edi, edi
        shr     rsi, 4
        mov     rdx, rsi
        shl     rdx, 4
L_025:  movdqu  xmm0, oword [rbp+rcx+8H]
        add     rdi, 1
        movdqu  oword [rax+rcx+8H], xmm0
        add     rcx, 16
        cmp     rsi, rdi
        ja      L_025
        cmp     rbx, rdx
        je      L_026
        lea     r14d, [rdx+8H]
        movsxd  r14, r14d
        movzx   ecx, byte [rbp+r14]
        mov     byte [rax+r14], cl
        lea     ecx, [rdx+1H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        jle     L_026
        lea     r10d, [rdx+9H]
        movsxd  rcx, r10d
        movzx   esi, byte [rbp+rcx]
        mov     byte [rax+rcx], sil
        lea     ecx, [rdx+2H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        jle     L_026
        lea     r9d, [rdx+0AH]
        movsxd  rcx, r9d
        movzx   esi, byte [rbp+rcx]
        mov     byte [rax+rcx], sil
        lea     ecx, [rdx+3H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        jle     L_026
        lea     r8d, [rdx+0BH]
        movsxd  rcx, r8d
        movzx   esi, byte [rbp+rcx]
        mov     byte [rax+rcx], sil
        lea     ecx, [rdx+4H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        jle     L_026
        lea     edi, [rdx+0CH]
        movsxd  rcx, edi
        movzx   esi, byte [rbp+rcx]
        mov     byte [rax+rcx], sil
        lea     ecx, [rdx+5H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        jle     L_026
        lea     esi, [rdx+0DH]
        movsxd  rcx, esi
        movzx   r11d, byte [rbp+rcx]
        mov     byte [rax+rcx], r11b
        lea     ecx, [rdx+6H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        jle     L_026
        lea     ecx, [rdx+0EH]
        movsxd  r11, ecx
        movzx   r15d, byte [rbp+r11]
        mov     byte [rax+r11], r15b
        lea     r11d, [rdx+7H]
        movsxd  r11, r11d
        cmp     rbx, r11
        jle     L_026
        lea     r11d, [rdx+0FH]
        cmp     rbx, r14
        movsxd  r11, r11d
        movzx   r15d, byte [rbp+r11]
        mov     byte [rax+r11], r15b
        jle     L_026
        lea     r11d, [rdx+10H]
        movsxd  r10, r10d
        cmp     rbx, r10
        movsxd  r11, r11d
        movzx   r14d, byte [rbp+r11]
        mov     byte [rax+r11], r14b
        jle     L_026
        lea     r10d, [rdx+11H]
        movsxd  r9, r9d
        cmp     rbx, r9
        movsxd  r10, r10d
        movzx   r11d, byte [rbp+r10]
        mov     byte [rax+r10], r11b
        jle     L_026
        lea     r9d, [rdx+12H]
        movsxd  r8, r8d
        cmp     rbx, r8
        movsxd  r9, r9d
        movzx   r10d, byte [rbp+r9]
        mov     byte [rax+r9], r10b
        jle     L_026
        lea     r8d, [rdx+13H]
        movsxd  rdi, edi
        cmp     rbx, rdi
        movsxd  r8, r8d
        movzx   r9d, byte [rbp+r8]
        mov     byte [rax+r8], r9b
        jle     L_026
        lea     edi, [rdx+14H]
        movsxd  rsi, esi
        cmp     rbx, rsi
        movsxd  rdi, edi
        movzx   r8d, byte [rbp+rdi]
        mov     byte [rax+rdi], r8b
        jle     L_026
        lea     esi, [rdx+15H]
        movsxd  rcx, ecx
        cmp     rbx, rcx
        movsxd  rsi, esi
        movzx   edi, byte [rbp+rsi]
        mov     byte [rax+rsi], dil
        jle     L_026
        add     edx, 22
        movsxd  rdx, edx
        movzx   ecx, byte [rbp+rdx]
        mov     byte [rax+rdx], cl
L_026:  test    r13, r13
        jle     L_031
        lea     rsi, [r13+1H]
        add     rbx, 8
        mov     edx, 1
        xor     ecx, ecx




ALIGN   8
L_027:  movzx   edi, byte [r12+rdx+7H]
        add     rcx, rax
        mov     byte [rcx+rbx], dil
        mov     rcx, rdx
        add     rdx, 1
        cmp     rdx, rsi
        jnz     L_027
L_028:  add     r13, rax
        mov     byte [r13+rbx], 0
        add     rsp, 8
        pop     rbx
        pop     rbp
        pop     r12
        pop     r13
        pop     r14
        pop     r15
        ret





ALIGN   8
L_029:  xor     edx, edx




ALIGN   8
L_030:  movzx   ecx, byte [rbp+rdx+8H]
        mov     byte [rax+rdx+8H], cl
        add     rdx, 1
        cmp     rbx, rdx
        jnz     L_030
        jmp     L_026

L_031:  add     rbx, 8
        jmp     L_028







ALIGN   16

__stringCompare:
        sub     rsp, 8
        add     rsi, 8
        add     rdi, 8
        call    strcmp
        add     rsp, 8
        cdqe
        ret



SECTION .data   


SECTION .bss    align=32

__value:
        resq    22472

__has:
        resq    22472

__real_addr:
        resq    108

__buffer.3442:
        resb    1048576


SECTION .text.startup 6

main:
        xor     eax, eax
        jmp     __init



SECTION .rodata.str1.1 

L_032:
        db 25H, 73H, 00H

L_033:
        db 25H, 6CH, 64H, 00H


;=====================================================================
	 section .text
_max:
	b0:
	push rbp
	mov rbp, rsp
	mov rax, rdi
	mov rcx, rsi
	cmp rax, rcx
	jg b1
	b2:
	mov rax, rcx
	jmp b3
	b1:
	b3:
	leave
	ret 
_equ:
	b4:
	push rbp
	mov rbp, rsp
	mov rcx, rdi
	mov rax, rsi
	cmp rcx, 0
	je b5
	b6:
	cmp rax, 0
	je b7
	b8:
	mov rcx, qword [rcx]
	cmp rcx, qword [rax]
	je b9
	b10:
	mov rax, 0
	jmp b11
	b9:
	mov rax, 1
	b11:
	jmp b12
	b7:
	b13:
	mov rax, 0
	b14:
	jmp b12
	b5:
	cmp rax, 0
	je b15
	b16:
	b17:
	mov rax, 0
	b18:
	jmp b12
	b15:
	b19:
	mov rax, 1
	b20:
	b12:
	leave
	ret 
_merge:
	b21:
	push rbp
	mov rbp, rsp
	push r13
	push r14
	push rbx
	push r15
	mov r13, rdi
	mov r15, rsi
	mov r14, rdx
	mov rcx, r15
	mov rax, r13
	add rax, 1
	mov rsi, rax
	mov rdi, rcx
	call _splay_tree.find
	mov rbx, rax
	mov rax, rbx
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov rax, r15
	mov qword [rax], rbx
	mov rax, r15
	mov rsi, r13
	mov rdi, rax
	call _splay_tree.find
	mov rbx, rax
	mov rcx, rbx
	mov rax, r15
	mov rsi, qword [rax]
	mov rdi, rcx
	call _Node.rotto
	mov rax, rbx
	mov rcx, qword [rax + 80]
	mov rax, r14
	mov rax, qword [rax]
	mov qword [rcx + 16], rax
	mov rax, r14
	mov rax, qword [rax]
	mov qword [rax + 88], rbx
	mov rax, rbx
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov rax, r15
	mov qword [rax], rbx
	b22:
	pop r15
	pop rbx
	pop r14
	pop r13
	leave
	ret 
_main:
	b23:
	push rbp
	mov rbp, rsp
	sub rsp, 16
	push r12
	push r13
	push r14
	push rbx
	push r15
	mov r15, qword [g_0]
	mov rax, qword [g_1]
	mov qword [g_1], rax
	mov rax, qword [g_2]
	mov qword [g_2], rax
	mov rax, qword [g_3]
	mov qword [g_3], rax
	mov rax, qword [g_4]
	mov qword [g_4], rax
	mov rax, qword [g_5]
	mov qword [g_5], rax
	mov rax, qword [g_6]
	mov qword [g_6], rax
	call __getInt
	mov qword [g_1], rax
	call __getInt
	mov qword [g_5], rax
	mov rdi, 8
	call malloc
	mov r15, rax
	mov rdi, r15
	call _splay_tree.splay_tree
	mov rax, r15
	mov qword [g_2], rax
	mov rdi, 8
	call malloc
	mov r15, rax
	mov rdi, r15
	call _splay_tree.splay_tree
	mov rax, qword [g_4]
	mov rcx, rax
	neg rcx
	mov rax, qword [g_3]
	mov qword [rax + 8], rcx
	mov rax, qword [g_1]
	mov rdx, rax
	add rdx, 1
	mov rax, qword [g_4]
	mov rcx, rax
	neg rcx
	mov rax, qword [g_3]
	mov qword [rax + rdx * 8 + 8], rcx
	mov rax, 1
	mov qword [rbp - 8], rax
	b24:
	mov rax, qword [rbp - 8]
	mov rcx, qword [g_1]
	cmp rax, rcx
	jle b25
	b26:
	mov rax, qword [g_2]
	mov rcx, rax
	mov rax, qword [g_1]
	mov rdx, rax
	add rdx, 1
	mov rax, qword [g_3]
	mov qword [g_3], rax
	mov rsi, 0
	mov rdi, rcx
	call _splay_tree.build_tree
	mov rax, qword [g_3]
	mov qword [g_3], rax
	mov rax, 1
	mov qword [rbp - 8], rax
	b27:
	mov rax, qword [rbp - 8]
	mov rcx, qword [g_5]
	cmp rax, rcx
	jle b28
	b29:
	mov rax, 0
	b30:
	mov qword [g_0], r15
	mov rcx, qword [g_1]
	mov qword [g_1], rcx
	mov rcx, qword [g_2]
	mov qword [g_2], rcx
	mov rcx, qword [g_3]
	mov qword [g_3], rcx
	mov rcx, qword [g_4]
	mov qword [g_4], rcx
	mov rcx, qword [g_5]
	mov qword [g_5], rcx
	mov rcx, qword [g_6]
	mov qword [g_6], rcx
	pop r15
	pop rbx
	pop r14
	pop r13
	pop r12
	leave
	ret 
	b28:
	call __getString
	mov r14, rax
	mov rax, r14
	mov rsi, 0
	mov rdi, rax
	call __string_ord
	mov rbx, rax
	mov rax, qword [g_6]
	mov rsi, 0
	mov rdi, rax
	call __string_ord
	cmp rbx, rax
	jne b31
	b32:
	call __getInt
	mov r12, rax
	call __getInt
	mov r13, rax
	mov rbx, 1
	b33:
	cmp rbx, r13
	jle b34
	b35:
	mov rcx, r15
	mov rax, qword [g_3]
	mov qword [g_3], rax
	mov rdx, r13
	mov rsi, 1
	mov rdi, rcx
	call _splay_tree.build_tree
	mov rax, qword [g_3]
	mov qword [g_3], rax
	mov rax, r12
	add rax, 1
	mov rdi, rax
	mov rax, qword [g_2]
	mov rcx, rax
	mov rax, r15
	mov rdx, rax
	mov rsi, rcx
	call _merge
	b31:
	mov rax, r14
	mov rsi, 0
	mov rdi, rax
	call __string_ord
	mov rbx, rax
	mov rax, qword [g_6]
	mov rsi, 1
	mov rdi, rax
	call __string_ord
	cmp rbx, rax
	jne b36
	b37:
	call __getInt
	mov rbx, rax
	call __getInt
	mov rdx, rax
	mov rax, qword [g_2]
	mov rsi, rbx
	add rsi, 1
	mov rcx, rbx
	add rcx, rdx
	mov rdx, rcx
	mov rdi, rax
	call _splay_tree.del
	b36:
	mov rax, r14
	mov rsi, 0
	mov rdi, rax
	call __string_ord
	cmp rax, 82
	jne b38
	b39:
	call __getInt
	mov rbx, rax
	call __getInt
	mov rcx, rax
	mov rax, qword [g_2]
	mov rsi, rbx
	add rsi, 1
	mov rdx, rbx
	add rdx, rcx
	mov rdi, rax
	call _splay_tree.rol
	b38:
	mov rax, r14
	mov rsi, 0
	mov rdi, rax
	call __string_ord
	cmp rax, 71
	jne b40
	b41:
	call __getInt
	mov rbx, rax
	call __getInt
	mov rcx, rax
	cmp rcx, 0
	jg b42
	b43:
	mov rax, g_7
	mov rdi, rax
	call __print
	jmp b44
	b42:
	mov rax, qword [g_2]
	mov rdi, rax
	mov rax, rbx
	add rax, 1
	mov rdx, rbx
	add rdx, rcx
	mov rsi, rax
	call _splay_tree.getsum
	mov rdi, rax
	call __toString
	mov rdi, rax
	call __println
	b44:
	b40:
	mov rax, r14
	mov rsi, 0
	mov rdi, rax
	call __string_ord
	cmp rax, 77
	jne b45
	b46:
	mov rax, r14
	mov rsi, 2
	mov rdi, rax
	call __string_ord
	cmp rax, 75
	je b47
	b48:
	mov rax, qword [g_2]
	mov rdi, rax
	call _splay_tree.getMax
	mov rdi, rax
	call __toString
	mov rsi, g_8
	mov rdi, rax
	call __stringConcate
	mov rdi, rax
	call __print
	jmp b49
	b47:
	call __getInt
	mov rbx, rax
	call __getInt
	mov r14, rax
	call __getInt
	mov rcx, qword [g_2]
	mov rdi, rcx
	mov rsi, rbx
	add rsi, 1
	mov rdx, rbx
	add rdx, r14
	mov rcx, rax
	call _splay_tree.change
	b49:
	b45:
	b50:
	mov rax, qword [rbp - 8]
	inc rax
	mov qword [rbp - 8], rax
	jmp b27
	b34:
	call __getInt
	mov rcx, rax
	mov rax, qword [g_3]
	mov qword [rax + rbx * 8 + 8], rcx
	b51:
	inc rbx
	jmp b33
	b25:
	call __getInt
	mov rdx, qword [g_3]
	mov rcx, qword [rbp - 8]
	mov qword [rdx + rcx * 8 + 8], rax
	b52:
	mov rax, qword [rbp - 8]
	mov rax, qword [rbp - 8]
	inc rax
	mov qword [rbp - 8], rax
	jmp b24
_Node.copy:
	b53:
	push rbp
	mov rbp, rsp
	mov rdx, rdi
	mov rcx, rsi
	mov rax, rcx
	mov rax, qword [rax]
	mov qword [rdx], rax
	mov rax, rcx
	mov rax, qword [rax + 8]
	mov qword [rdx + 8], rax
	mov rax, rcx
	mov rax, qword [rax + 16]
	mov qword [rdx + 16], rax
	mov rax, rcx
	mov rax, qword [rax + 24]
	mov qword [rdx + 24], rax
	mov rax, rcx
	mov rax, qword [rax + 32]
	mov qword [rdx + 32], rax
	mov rax, rcx
	mov rax, qword [rax + 40]
	mov qword [rdx + 40], rax
	mov rax, rcx
	mov rax, qword [rax + 48]
	mov qword [rdx + 48], rax
	mov rax, rcx
	mov rax, qword [rax + 56]
	mov qword [rdx + 56], rax
	mov rax, rcx
	mov rax, qword [rax + 64]
	mov qword [rdx + 64], rax
	mov rax, rcx
	mov rax, qword [rax + 72]
	mov qword [rdx + 72], rax
	mov rsi, qword [rdx + 80]
	mov rax, rcx
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 8]
	mov qword [rsi + 8], rax
	mov rsi, qword [rdx + 80]
	mov rax, rcx
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 16]
	mov qword [rsi + 16], rax
	mov rax, rcx
	mov rax, qword [rax + 88]
	mov qword [rdx + 88], rax
	b54:
	leave
	ret 
_Node.init:
	b55:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov rbx, rdi
	mov rcx, rsi
	mov rax, rdx
	mov qword [rbx], rax
	mov qword [rbx + 16], 1
	mov qword [rbx + 32], 0
	mov qword [rbx + 48], 0
	mov qword [rbx + 40], 0
	mov qword [rbx + 24], rcx
	mov qword [rbx + 8], rcx
	mov qword [rbx + 56], rcx
	mov qword [rbx + 64], rcx
	mov qword [rbx + 72], rcx
	mov r14, 2
	lea rax, [r14 * 8 + 8]
	mov rdi, rax
	call malloc
	mov r15, rax
	mov qword [r15], r14
	b56:
	cmp r14, 0
	jg b57
	b58:
	mov qword [rbx + 80], r15
	mov rax, qword [rbx + 80]
	mov qword [rax + 8], 0
	mov rax, qword [rbx + 80]
	mov qword [rax + 16], 0
	mov qword [rbx + 88], 0
	b59:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
	b57:
	mov rdi, 8
	call malloc
	mov qword [rax], 0
	mov qword [r15 + r14 * 8], rax
	dec r14
	jmp b56
_Node.judge:
	b60:
	push rbp
	mov rbp, rsp
	mov rax, rdi
	mov rcx, rsi
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 8]
	mov rsi, rcx
	mov rdi, rax
	call _equ
	cmp rax, 0
	jne b61
	b62:
	mov rax, 1
	jmp b63
	b61:
	mov rax, 0
	b63:
	leave
	ret 
_Node.push_up:
	b64:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov rbx, rdi
	mov qword [rbx + 16], 1
	mov rax, qword [rbx + 8]
	mov qword [rbx + 24], rax
	mov r15, 0
	b65:
	cmp r15, 2
	jl b66
	b67:
	mov rax, qword [rbx + 8]
	mov qword [rbx + 56], rax
	mov rax, qword [rbx + 8]
	mov qword [rbx + 64], rax
	mov rax, qword [rbx + 8]
	mov qword [rbx + 72], rax
	mov r15, qword [rbx + 8]
	mov r14, qword [rbx + 8]
	mov rax, qword [rbx + 80]
	cmp qword [rax + 8], 0
	je b68
	b69:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 8]
	mov rax, qword [rax + 56]
	mov qword [rbx + 56], rax
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 8]
	add r15, qword [rax + 24]
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 8]
	mov rax, qword [rax + 64]
	mov rcx, 0
	mov rsi, rcx
	mov rdi, rax
	call _max
	add r14, rax
	b68:
	mov rax, qword [rbx + 80]
	cmp qword [rax + 16], 0
	je b70
	b71:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 16]
	mov rax, qword [rax + 64]
	mov qword [rbx + 64], rax
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 16]
	add r14, qword [rax + 24]
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 16]
	mov rax, qword [rax + 56]
	mov rcx, 0
	mov rsi, rcx
	mov rdi, rax
	call _max
	add r15, rax
	b70:
	mov rax, qword [rbx + 56]
	mov rcx, r15
	mov rsi, rcx
	mov rdi, rax
	call _max
	mov qword [rbx + 56], rax
	mov rcx, qword [rbx + 64]
	mov rax, r14
	mov rsi, rax
	mov rdi, rcx
	call _max
	mov qword [rbx + 64], rax
	mov r15, 0
	b72:
	cmp r15, 2
	jl b73
	b74:
	mov r15, qword [rbx + 8]
	mov rax, qword [rbx + 80]
	cmp qword [rax + 8], 0
	je b75
	b76:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 8]
	mov rcx, qword [rax + 64]
	mov rax, 0
	mov rsi, rax
	mov rdi, rcx
	call _max
	add r15, rax
	b75:
	mov rax, qword [rbx + 80]
	cmp qword [rax + 16], 0
	je b77
	b78:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + 16]
	mov rcx, qword [rax + 56]
	mov rax, 0
	mov rsi, rax
	mov rdi, rcx
	call _max
	add r15, rax
	b77:
	mov rax, qword [rbx + 72]
	mov rcx, r15
	mov rsi, rcx
	mov rdi, rax
	call _max
	mov qword [rbx + 72], rax
	b79:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
	b73:
	mov rax, qword [rbx + 80]
	cmp qword [rax + r15 * 8 + 8], 0
	je b80
	b81:
	mov rcx, qword [rbx + 72]
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + r15 * 8 + 8]
	mov rax, qword [rax + 72]
	mov rsi, rax
	mov rdi, rcx
	call _max
	mov qword [rbx + 72], rax
	b80:
	b82:
	inc r15
	jmp b72
	b66:
	mov rax, qword [rbx + 80]
	cmp qword [rax + r15 * 8 + 8], 0
	je b83
	b84:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + r15 * 8 + 8]
	mov rcx, qword [rbx + 16]
	add rcx, qword [rax + 16]
	mov qword [rbx + 16], rcx
	mov rax, qword [rbx + 80]
	mov rcx, qword [rax + r15 * 8 + 8]
	mov rax, qword [rbx + 24]
	add rax, qword [rcx + 24]
	mov qword [rbx + 24], rax
	b83:
	b85:
	inc r15
	jmp b65
_Node.addtag_ch:
	b86:
	push rbp
	mov rbp, rsp
	mov rcx, rsi
	mov qword [rdi + 8], rcx
	mov rax, qword [rdi + 16]
	imul rcx
	mov qword [rdi + 24], rax
	cmp rcx, 0
	jge b87
	b88:
	mov qword [rdi + 56], rcx
	mov qword [rdi + 64], rcx
	mov qword [rdi + 72], rcx
	jmp b89
	b87:
	mov rax, qword [rdi + 24]
	mov qword [rdi + 56], rax
	mov rax, qword [rdi + 24]
	mov qword [rdi + 64], rax
	mov rax, qword [rdi + 24]
	mov qword [rdi + 72], rax
	b89:
	mov qword [rdi + 32], 1
	mov qword [rdi + 40], rcx
	b90:
	leave
	ret 
_Node.addtag_ro:
	b91:
	push rbp
	mov rbp, rsp
	mov rdx, rdi
	mov rax, qword [rdx + 80]
	mov rcx, qword [rax + 8]
	mov rsi, qword [rdx + 80]
	mov rax, qword [rdx + 80]
	mov rax, qword [rax + 16]
	mov qword [rsi + 8], rax
	mov rax, qword [rdx + 80]
	mov qword [rax + 16], rcx
	mov rax, qword [rdx + 56]
	mov rcx, qword [rdx + 64]
	mov qword [rdx + 56], rcx
	mov qword [rdx + 64], rax
	mov rax, qword [rdx + 48]
	xor rax, 1
	mov qword [rdx + 48], rax
	b92:
	leave
	ret 
_Node.push_down:
	b93:
	push rbp
	mov rbp, rsp
	push rbx
	push r15
	mov rbx, rdi
	cmp qword [rbx + 32], 1
	jne b94
	b95:
	mov r15, 0
	b96:
	cmp r15, 2
	jl b97
	b98:
	mov qword [rbx + 32], 0
	b94:
	cmp qword [rbx + 48], 1
	jne b99
	b100:
	mov r15, 0
	b101:
	cmp r15, 2
	jl b102
	b103:
	mov qword [rbx + 48], 0
	b99:
	b104:
	pop r15
	pop rbx
	leave
	ret 
	b102:
	mov rax, qword [rbx + 80]
	cmp qword [rax + r15 * 8 + 8], 0
	je b105
	b106:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + r15 * 8 + 8]
	mov rdi, rax
	call _Node.addtag_ro
	b105:
	b107:
	inc r15
	jmp b101
	b97:
	mov rax, qword [rbx + 80]
	cmp qword [rax + r15 * 8 + 8], 0
	je b108
	b109:
	mov rax, qword [rbx + 80]
	mov rax, qword [rax + r15 * 8 + 8]
	mov rsi, qword [rbx + 40]
	mov rdi, rax
	call _Node.addtag_ch
	b108:
	b110:
	inc r15
	jmp b96
_Node.rot:
	b111:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov rbx, rdi
	mov r15, qword [rbx + 88]
	mov rax, r15
	mov rsi, rbx
	mov rdi, rax
	call _Node.judge
	mov rcx, rax
	mov rax, r15
	mov rsi, qword [rax + 80]
	mov rax, rcx
	xor rax, 1
	mov rdx, qword [rbx + 80]
	mov rax, qword [rdx + rax * 8 + 8]
	mov qword [rsi + rcx * 8 + 8], rax
	mov rdx, rcx
	xor rdx, 1
	mov rax, qword [rbx + 80]
	mov qword [rax + rdx * 8 + 8], r15
	mov rax, r15
	mov rax, qword [rax + 80]
	cmp qword [rax + rcx * 8 + 8], 0
	je b112
	b113:
	mov rax, r15
	mov rax, qword [rax + 80]
	mov rax, qword [rax + rcx * 8 + 8]
	mov qword [rax + 88], r15
	b112:
	mov rax, r15
	mov rax, qword [rax + 88]
	mov qword [rbx + 88], rax
	mov rax, r15
	mov qword [rax + 88], rbx
	cmp qword [rbx + 88], 0
	je b114
	b115:
	mov r14, qword [rbx + 88]
	mov rax, qword [rbx + 88]
	mov rsi, r15
	mov rdi, rax
	call _Node.judge
	mov rcx, qword [r14 + 80]
	mov qword [rcx + rax * 8 + 8], rbx
	b114:
	mov rax, r15
	mov rdi, rax
	call _Node.push_up
	b116:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
_Node.rotto:
	b117:
	push rbp
	mov rbp, rsp
	push r13
	push r14
	push rbx
	push r15
	mov r14, rdi
	mov r13, rsi
	b118:
	mov rcx, qword [r14 + 88]
	mov rax, r13
	mov rsi, rax
	mov rdi, rcx
	call _equ
	cmp rax, 0
	jne b119
	b120:
	mov r15, qword [r14 + 88]
	mov rax, r15
	mov rcx, qword [rax + 88]
	mov rax, r13
	mov rsi, rax
	mov rdi, rcx
	call _equ
	cmp rax, 0
	jne b121
	b122:
	mov rax, r15
	mov rax, qword [rax + 88]
	mov rsi, r15
	mov rdi, rax
	call _Node.judge
	mov rbx, rax
	mov rax, r15
	mov rsi, r14
	mov rdi, rax
	call _Node.judge
	cmp rbx, rax
	je b123
	b124:
	mov rdi, r14
	call _Node.rot
	mov rdi, r14
	call _Node.rot
	jmp b125
	b123:
	mov rax, r15
	mov rdi, rax
	call _Node.rot
	mov rdi, r14
	call _Node.rot
	b125:
	jmp b118
	b121:
	mov rdi, r14
	call _Node.rot
	jmp b118
	b119:
	mov rdi, r14
	call _Node.push_up
	cmp qword [r14 + 88], 0
	je b126
	b127:
	mov rax, qword [r14 + 88]
	mov rdi, rax
	call _Node.push_up
	b126:
	b128:
	pop r15
	pop rbx
	pop r14
	pop r13
	leave
	ret 
_splay_tree.splay_tree:
	b129:
	push rbp
	mov rbp, rsp
	mov rax, rdi
	mov qword [rax], 0
	b130:
	leave
	ret 
_splay_tree.build:
	b131:
	push rbp
	mov rbp, rsp
	sub rsp, 48
	push r12
	push r13
	push r14
	push rbx
	push r15
	mov r15, rdi
	mov r14, rsi
	mov rax, rdx
	mov qword [rbp - 32], rax
	mov r13, rcx
	mov rax, r8
	mov qword [rbp - 16], rax
	mov rbx, qword [g_3]
	mov r12, qword [g_9]
	mov rcx, r13
	mov rax, qword [rbp - 16]
	add rcx, rax
	mov rax, rcx
	mov rcx, 1
	sar rax, cl
	mov qword [rbp - 8], rax
	mov rdi, 96
	call malloc
	mov qword [rbp - 40], rax
	mov rax, qword [rbp - 40]
	mov rcx, rax
	inc r12
	mov rdx, r12
	mov rax, qword [rbp - 8]
	mov rsi, qword [rbx + rax * 8 + 8]
	mov rdi, rcx
	call _Node.init
	mov rcx, r14
	mov rax, qword [rbp - 40]
	mov rsi, rax
	mov rdi, rcx
	call _Node.copy
	mov rax, r14
	mov rcx, qword [rbp - 32]
	mov qword [rax + 88], rcx
	mov rax, qword [rbp - 8]
	cmp r13, rax
	jge b132
	b133:
	mov rax, r14
	mov rax, qword [rax + 80]
	mov qword [rbp - 24], rax
	mov rdi, 96
	call malloc
	mov rcx, rax
	mov rax, qword [rbp - 24]
	mov qword [rax + 8], rcx
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 8]
	inc r12
	mov rdx, r12
	mov rsi, 0
	mov rdi, rax
	call _Node.init
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rsi, qword [rax + 8]
	mov rdx, r14
	mov rax, r13
	mov rcx, qword [rbp - 8]
	sub rcx, 1
	mov qword [g_3], rbx
	mov qword [g_9], r12
	mov r8, rcx
	mov rcx, rax
	mov rdi, r15
	call _splay_tree.build
	mov r12, qword [g_9]
	mov rbx, qword [g_3]
	b132:
	mov rcx, qword [rbp - 16]
	mov rax, qword [rbp - 8]
	cmp rcx, rax
	jle b134
	b135:
	mov rax, r14
	mov r13, qword [rax + 80]
	mov rdi, 96
	call malloc
	mov qword [r13 + 16], rax
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 16]
	inc r12
	mov rdx, r12
	mov rsi, 0
	mov rdi, rax
	call _Node.init
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rsi, qword [rax + 16]
	mov rdx, r14
	mov rax, qword [rbp - 8]
	add rax, 1
	mov rcx, rax
	mov rax, qword [rbp - 16]
	mov qword [g_3], rbx
	mov qword [g_9], r12
	mov r8, rax
	mov rdi, r15
	call _splay_tree.build
	mov r12, qword [g_9]
	mov rbx, qword [g_3]
	b134:
	mov rax, r14
	mov rdi, rax
	call _Node.push_up
	b136:
	mov qword [g_3], rbx
	mov qword [g_9], r12
	pop r15
	pop rbx
	pop r14
	pop r13
	pop r12
	leave
	ret 
_splay_tree.build_tree:
	b137:
	push rbp
	mov rbp, rsp
	push r13
	push r14
	push rbx
	push r15
	mov rbx, rdi
	mov r14, rsi
	mov r13, rdx
	mov r15, qword [g_9]
	mov rdi, 96
	call malloc
	mov qword [rbx], rax
	mov rax, qword [rbx]
	inc r15
	mov rdx, r15
	mov rsi, 0
	mov rdi, rax
	call _Node.init
	mov rsi, qword [rbx]
	mov rdx, 0
	mov rax, r14
	mov rcx, r13
	mov qword [g_9], r15
	mov r8, rcx
	mov rcx, rax
	mov rdi, rbx
	call _splay_tree.build
	mov r15, qword [g_9]
	b138:
	mov qword [g_9], r15
	pop r15
	pop rbx
	pop r14
	pop r13
	leave
	ret 
_splay_tree.find:
	b139:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov rax, rdi
	mov rbx, rsi
	mov r14, qword [rax]
	mov r15, 0
	mov rax, r14
	mov rax, qword [rax + 80]
	cmp qword [rax + 8], 0
	jne b140
	b141:
	mov rcx, 1
	jmp b142
	b140:
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 8]
	mov rax, qword [rax + 16]
	add rax, 1
	mov rcx, rax
	b142:
	b143:
	mov rax, r15
	add rax, rcx
	cmp rax, rbx
	jne b144
	b145:
	mov rax, r14
	b146:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
	b144:
	mov rax, r15
	add rax, rcx
	cmp rbx, rax
	jl b147
	b148:
	add r15, rcx
	mov rax, r14
	mov rax, qword [rax + 80]
	mov r14, qword [rax + 16]
	jmp b149
	b147:
	mov rax, r14
	mov rax, qword [rax + 80]
	mov r14, qword [rax + 8]
	b149:
	mov rax, r14
	mov rdi, rax
	call _Node.push_down
	mov rax, r14
	mov rax, qword [rax + 80]
	cmp qword [rax + 8], 0
	jne b150
	b151:
	mov rcx, 1
	jmp b152
	b150:
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 8]
	mov rax, qword [rax + 16]
	add rax, 1
	mov rcx, rax
	b152:
	jmp b143
_splay_tree.dfs:
	b153:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov r15, rdi
	mov r14, rsi
	mov rbx, 0
	b154:
	cmp rbx, 2
	jl b155
	b156:
	b157:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
	b155:
	mov rax, r14
	mov rax, qword [rax + 80]
	cmp qword [rax + rbx * 8 + 8], 0
	je b158
	b159:
	mov rax, r14
	mov rax, qword [rax + 80]
	mov rax, qword [rax + rbx * 8 + 8]
	mov rsi, rax
	mov rdi, r15
	call _splay_tree.dfs
	b158:
	b160:
	mov rax, rbx
	inc rbx
	jmp b154
_splay_tree.del:
	b161:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov r15, rdi
	mov r14, rsi
	mov rax, rdx
	add rax, 1
	mov rsi, rax
	mov rdi, r15
	call _splay_tree.find
	mov rbx, rax
	mov rax, rbx
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [r15], rbx
	mov rax, r14
	sub rax, 1
	mov rsi, rax
	mov rdi, r15
	call _splay_tree.find
	mov rbx, rax
	mov rax, rbx
	mov rsi, qword [r15]
	mov rdi, rax
	call _Node.rotto
	mov rax, rbx
	mov rax, qword [rax + 80]
	cmp qword [rax + 16], 0
	je b162
	b163:
	mov rax, rbx
	mov rax, qword [rax + 80]
	mov rax, qword [rax + 16]
	mov rsi, rax
	mov rdi, r15
	call _splay_tree.dfs
	b162:
	mov rax, rbx
	mov rax, qword [rax + 80]
	mov qword [rax + 16], 0
	mov rax, rbx
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [r15], rbx
	b164:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
_splay_tree.change:
	b165:
	push rbp
	mov rbp, rsp
	push r13
	push r14
	push rbx
	push r15
	mov rbx, rdi
	mov r13, rsi
	mov rax, rdx
	mov r14, rcx
	add rax, 1
	mov rsi, rax
	mov rdi, rbx
	call _splay_tree.find
	mov r15, rax
	mov rax, r15
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [rbx], r15
	mov rax, r13
	sub rax, 1
	mov rsi, rax
	mov rdi, rbx
	call _splay_tree.find
	mov r15, rax
	mov rax, r15
	mov rsi, qword [rbx]
	mov rdi, rax
	call _Node.rotto
	mov rax, r15
	mov rax, qword [rax + 80]
	mov r15, qword [rax + 16]
	mov rax, r15
	mov rdi, rax
	call _Node.push_down
	mov rax, r15
	mov rsi, r14
	mov rdi, rax
	call _Node.addtag_ch
	mov rax, r15
	mov rdi, rax
	call _Node.push_down
	mov rax, r15
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [rbx], r15
	b166:
	pop r15
	pop rbx
	pop r14
	pop r13
	leave
	ret 
_splay_tree.rol:
	b167:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov r14, rdi
	mov rbx, rsi
	mov rax, rdx
	add rax, 1
	mov rsi, rax
	mov rdi, r14
	call _splay_tree.find
	mov r15, rax
	mov rax, r15
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [r14], r15
	mov rax, rbx
	sub rax, 1
	mov rsi, rax
	mov rdi, r14
	call _splay_tree.find
	mov r15, rax
	mov rax, r15
	mov rsi, qword [r14]
	mov rdi, rax
	call _Node.rotto
	mov rax, r15
	mov rax, qword [rax + 80]
	mov r15, qword [rax + 16]
	mov rax, r15
	mov rdi, rax
	call _Node.push_down
	mov rax, r15
	mov rdi, rax
	call _Node.addtag_ro
	mov rax, r15
	mov rdi, rax
	call _Node.push_down
	mov rax, r15
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [r14], r15
	b168:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
_splay_tree.getsum:
	b169:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov r15, rdi
	mov r14, rsi
	mov rax, rdx
	add rax, 1
	mov rsi, rax
	mov rdi, r15
	call _splay_tree.find
	mov rbx, rax
	mov rax, rbx
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [r15], rbx
	mov rax, r14
	sub rax, 1
	mov rsi, rax
	mov rdi, r15
	call _splay_tree.find
	mov rbx, rax
	mov rax, rbx
	mov rsi, qword [r15]
	mov rdi, rax
	call _Node.rotto
	mov rax, rbx
	mov rax, qword [rax + 80]
	mov rbx, qword [rax + 16]
	mov rax, rbx
	mov rdi, rax
	call _Node.push_down
	mov rax, rbx
	mov r14, qword [rax + 24]
	mov rax, rbx
	mov rsi, 0
	mov rdi, rax
	call _Node.rotto
	mov qword [r15], rbx
	mov rax, r14
	b170:
	pop r15
	pop rbx
	pop r14
	leave
	ret 
_splay_tree.getMax:
	b171:
	push rbp
	mov rbp, rsp
	push r15
	mov r15, rdi
	mov rax, qword [r15]
	mov rdi, rax
	call _Node.push_down
	mov rax, qword [r15]
	mov rax, qword [rax + 72]
	b172:
	pop r15
	leave
	ret 
__init:
	b173:
	push rbp
	mov rbp, rsp
	push r14
	push rbx
	push r15
	mov rbx, 1000000000
	mov rax, 4000010
	mov r15, 0
	mov r14, rax
	lea rax, [r14 * 8 + 8]
	mov rdi, rax
	call malloc
	mov qword [rax], r14
	b174:
	cmp r14, 0
	jg b175
	b176:
	mov rcx, rax
	mov rdx, g_10
	mov qword [g_9], r15
	mov qword [g_3], rcx
	mov qword [g_4], rbx
	mov qword [g_6], rdx
	call _main
	mov rdx, qword [g_6]
	mov rbx, qword [g_4]
	mov rcx, qword [g_3]
	mov r15, qword [g_9]
	pop r15
	pop rbx
	pop r14
	leave
	ret 
	b175:
	mov qword [rax + r14 * 8], 0
	dec r14
	jmp b174
	section .data
g_4:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_11:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_1:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_5:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_9:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_3:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_2:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_0:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_6:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_7:
	dq 2
	db 30H, 0AH, 00H
g_8:
	dq 1
	db 0AH, 00H
g_10:
	dq 2
	db 49H, 44H, 00H
