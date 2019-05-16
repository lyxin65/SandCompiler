





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
_random:
	b0:
	push rbp
	mov rbp, rsp
	mov r8, qword [g_0]
	mov rcx, qword [g_1]
	mov r9, qword [g_2]
	mov rsi, qword [g_3]
	mov rdi, qword [g_4]
	mov rax, rdi
	cdq
	idiv r9
	mov rax, rcx
	imul rdx
	mov r10, rax
	mov rax, rdi
	cdq
	idiv r9
	mov rdx, rax
	mov rax, r8
	imul rdx
	mov rdx, r10
	sub rdx, rax
	mov rax, rdx
	cmp rax, 0
	jge b1
	b2:
	add rax, rsi
	mov rdi, rax
	jmp b3
	b1:
	mov rdi, rax
	b3:
	mov rax, rdi
	b4:
	mov qword [g_0], r8
	mov qword [g_1], rcx
	mov qword [g_2], r9
	mov qword [g_3], rsi
	mov qword [g_4], rdi
	leave
	ret 
_initialize:
	b5:
	push rbp
	mov rbp, rsp
	mov rcx, rdi
	mov rax, qword [g_4]
	mov rax, rcx
	b6:
	mov qword [g_4], rax
	leave
	ret 
_swap:
	b7:
	push rbp
	mov rbp, rsp
	mov rdx, rdi
	mov rcx, rsi
	mov rax, qword [g_5]
	mov rdi, qword [rax + rdx * 8 + 8]
	mov rsi, qword [rax + rcx * 8 + 8]
	mov qword [rax + rdx * 8 + 8], rsi
	mov qword [rax + rcx * 8 + 8], rdi
	b8:
	mov qword [g_5], rax
	leave
	ret 
_pd:
	b9:
	push rbp
	mov rbp, rsp
	mov rsi, rdi
	mov rdi, qword [g_6]
	b10:
	cmp rdi, rsi
	jle b11
	b12:
	b13:
	mov rax, 0
	b14:
	jmp b15
	b11:
	mov rcx, rdi
	add rcx, 1
	mov rax, rdi
	imul rcx
	cdq
	mov rcx, 2
	idiv rcx
	cmp rsi, rax
	je b16
	b17:
	b18:
	inc rdi
	jmp b10
	b16:
	b19:
	mov rax, 1
	b20:
	b15:
	mov qword [g_6], rdi
	leave
	ret 
_show:
	b21:
	push rbp
	mov rbp, rsp
	push r13
	push r14
	push r12
	mov r13, qword [g_7]
	mov r12, qword [g_5]
	mov r14, 0
	b22:
	cmp r14, r13
	jl b23
	b24:
	mov rdi, g_8
	call __println
	b25:
	mov qword [g_7], r13
	mov qword [g_5], r12
	pop r12
	pop r14
	pop r13
	leave
	ret 
	b23:
	mov rdi, qword [r12 + r14 * 8 + 8]
	call __toString
	mov rsi, g_9
	mov rdi, rax
	call __stringConcate
	mov rdi, rax
	call __print
	b26:
	inc r14
	jmp b22
_win:
	b27:
	push rbp
	mov rbp, rsp
	push r15
	push r13
	push r14
	push r12
	mov r15, qword [g_6]
	mov r13, qword [g_7]
	mov r12, qword [g_5]
	mov r14, 100
	lea rax, [r14 * 8 + 8]
	mov rdi, rax
	call malloc
	mov qword [rax], r14
	b28:
	cmp r14, 0
	jg b29
	b30:
	mov rdx, rax
	cmp r13, r15
	jne b31
	b32:
	mov rsi, 0
	b33:
	cmp rsi, r13
	jl b34
	b35:
	mov rcx, 0
	b36:
	mov rax, r13
	sub rax, 1
	cmp rcx, rax
	jl b37
	b38:
	mov rcx, 0
	b39:
	cmp rcx, r13
	jl b40
	b41:
	b42:
	mov rax, 1
	b43:
	jmp b44
	b40:
	mov rax, rcx
	add rax, 1
	cmp qword [rdx + rcx * 8 + 8], rax
	jne b45
	b46:
	b47:
	inc rcx
	jmp b39
	b45:
	b48:
	mov rax, 0
	b49:
	jmp b44
	b37:
	mov rax, rcx
	add rax, 1
	mov rsi, rax
	b50:
	cmp rsi, r13
	jl b51
	b52:
	b53:
	inc rcx
	jmp b36
	b51:
	mov rax, qword [rdx + rcx * 8 + 8]
	cmp rax, qword [rdx + rsi * 8 + 8]
	jle b54
	b55:
	mov rdi, qword [rdx + rcx * 8 + 8]
	mov rax, qword [rdx + rsi * 8 + 8]
	mov qword [rdx + rcx * 8 + 8], rax
	mov qword [rdx + rsi * 8 + 8], rdi
	b54:
	b56:
	inc rsi
	jmp b50
	b34:
	mov rax, qword [r12 + rsi * 8 + 8]
	mov qword [rdx + rsi * 8 + 8], rax
	b57:
	inc rsi
	jmp b33
	b31:
	b58:
	mov rax, 0
	b59:
	b44:
	mov qword [g_6], r15
	mov qword [g_7], r13
	mov qword [g_5], r12
	pop r12
	pop r14
	pop r13
	pop r15
	leave
	ret 
	b29:
	mov qword [rax + r14 * 8], 0
	dec r14
	jmp b28
_merge:
	b60:
	push rbp
	mov rbp, rsp
	push r12
	push r14
	mov r14, qword [g_7]
	mov rcx, qword [g_5]
	mov r12, 0
	b61:
	cmp r12, r14
	jl b62
	b63:
	mov r12, 0
	b64:
	cmp r12, r14
	jge b65
	b66:
	cmp qword [rcx + r12 * 8 + 8], 0
	je b67
	b68:
	b69:
	inc r12
	jmp b64
	b67:
	mov r14, r12
	b65:
	b70:
	mov qword [g_7], r14
	mov qword [g_5], rcx
	pop r14
	pop r12
	leave
	ret 
	b62:
	cmp qword [rcx + r12 * 8 + 8], 0
	jne b71
	b72:
	mov rax, r12
	add rax, 1
	b73:
	cmp rax, r14
	jge b74
	b75:
	cmp qword [rcx + rax * 8 + 8], 0
	jne b76
	b77:
	b78:
	inc rax
	jmp b73
	b76:
	mov qword [g_5], rcx
	mov rsi, rax
	mov rdi, r12
	call _swap
	mov rcx, qword [g_5]
	b74:
	b71:
	b79:
	inc r12
	jmp b61
_move:
	b80:
	push rbp
	mov rbp, rsp
	mov rdx, qword [g_7]
	mov rcx, qword [g_5]
	mov rax, 0
	b81:
	cmp rax, rdx
	jl b82
	b83:
	mov qword [rcx + rdx * 8 + 8], rdx
	mov rax, rdx
	inc rdx
	b84:
	mov qword [g_7], rdx
	mov qword [g_5], rcx
	leave
	ret 
	b82:
	dec qword [rcx + rax * 8 + 8]
	add rax, 1
	jmp b81
_main:
	b85:
	push rbp
	mov rbp, rsp
	sub rsp, 16
	push rbx
	push r15
	push r13
	push r14
	push r12
	mov rbx, qword [g_0]
	mov rax, qword [g_6]
	mov qword [g_6], rax
	mov r13, qword [g_1]
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov r12, qword [g_2]
	mov rax, qword [g_3]
	mov qword [g_3], rax
	mov r15, qword [g_5]
	mov rax, qword [g_10]
	mov qword [g_10], rax
	mov rax, 0
	mov qword [rbp - 8], rax
	mov rax, 0
	mov qword [rbp - 16], rax
	mov r14, 0
	mov rax, 3
	mov rcx, 7
	imul rcx
	mov rcx, 10
	imul rcx
	mov qword [g_10], rax
	mov rax, 0
	mov qword [g_6], rax
	mov r12, 100
	lea rax, [r12 * 8 + 8]
	mov rdi, rax
	call malloc
	mov qword [rax], r12
	b86:
	cmp r12, 0
	jg b87
	b88:
	mov r15, rax
	mov rax, qword [g_3]
	cdq
	idiv r13
	mov r12, rax
	mov rax, qword [g_3]
	cdq
	idiv r13
	mov rax, rdx
	mov rbx, rax
	mov rax, qword [g_6]
	mov qword [g_6], rax
	mov rax, qword [g_10]
	mov rdi, rax
	call _pd
	mov rcx, qword [g_6]
	mov qword [g_6], rcx
	cmp rax, 0
	jne b89
	b90:
	mov rdi, g_11
	call __println
	mov rax, 1
	jmp b91
	b89:
	mov rdi, g_12
	call __println
	mov rdi, 3654898
	call _initialize
	mov qword [g_0], rbx
	mov qword [g_1], r13
	mov qword [g_2], r12
	mov rax, qword [g_3]
	mov qword [g_3], rax
	call _random
	mov rcx, qword [g_3]
	mov qword [g_3], rcx
	mov r12, qword [g_2]
	mov r13, qword [g_1]
	mov rbx, qword [g_0]
	cdq
	mov rcx, 10
	idiv rcx
	mov rax, rdx
	add rax, 1
	mov qword [g_7], rax
	mov rax, qword [g_7]
	mov rdi, rax
	call __toString
	mov rdi, rax
	call __println
	b92:
	mov rax, qword [g_7]
	sub rax, 1
	mov rcx, qword [rbp - 8]
	cmp rcx, rax
	jl b93
	b94:
	mov rax, qword [g_7]
	mov rdx, rax
	sub rdx, 1
	mov rax, qword [g_10]
	mov rcx, qword [rbp - 16]
	sub rax, rcx
	mov qword [r15 + rdx * 8 + 8], rax
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov qword [g_5], r15
	call _show
	mov r15, qword [g_5]
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov qword [g_5], r15
	call _merge
	mov r15, qword [g_5]
	mov rax, qword [g_7]
	mov qword [g_7], rax
	b95:
	mov rax, qword [g_6]
	mov qword [g_6], rax
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov qword [g_5], r15
	call _win
	mov r15, qword [g_5]
	mov rcx, qword [g_7]
	mov qword [g_7], rcx
	mov rcx, qword [g_6]
	mov qword [g_6], rcx
	cmp rax, 0
	jne b96
	b97:
	inc r14
	mov rdi, r14
	call __toString
	mov rsi, rax
	mov rdi, g_13
	call __stringConcate
	mov rsi, g_14
	mov rdi, rax
	call __stringConcate
	mov rdi, rax
	call __println
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov qword [g_5], r15
	call _move
	mov r15, qword [g_5]
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov qword [g_5], r15
	call _merge
	mov r15, qword [g_5]
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov rax, qword [g_7]
	mov qword [g_7], rax
	mov qword [g_5], r15
	call _show
	mov r15, qword [g_5]
	mov rax, qword [g_7]
	mov qword [g_7], rax
	jmp b95
	b96:
	mov rdi, r14
	call __toString
	mov rsi, rax
	mov rdi, g_15
	call __stringConcate
	mov rsi, g_16
	mov rdi, rax
	call __stringConcate
	mov rdi, rax
	call __println
	mov rax, 0
	b91:
	mov qword [g_0], rbx
	mov rcx, qword [g_6]
	mov qword [g_6], rcx
	mov qword [g_1], r13
	mov rcx, qword [g_7]
	mov qword [g_7], rcx
	mov qword [g_2], r12
	mov rcx, qword [g_3]
	mov qword [g_3], rcx
	mov qword [g_5], r15
	mov rcx, qword [g_10]
	mov qword [g_10], rcx
	pop r12
	pop r14
	pop r13
	pop r15
	pop rbx
	leave
	ret 
	b93:
	mov qword [g_0], rbx
	mov qword [g_1], r13
	mov qword [g_2], r12
	mov rax, qword [g_3]
	mov qword [g_3], rax
	call _random
	mov rcx, qword [g_3]
	mov qword [g_3], rcx
	mov r12, qword [g_2]
	mov r13, qword [g_1]
	mov rbx, qword [g_0]
	cdq
	mov rcx, 10
	idiv rcx
	mov rax, rdx
	mov rcx, rax
	add rcx, 1
	mov rax, qword [rbp - 8]
	mov qword [r15 + rax * 8 + 8], rcx
	b98:
	mov rax, qword [rbp - 8]
	mov rax, qword [r15 + rax * 8 + 8]
	mov rcx, qword [rbp - 16]
	add rax, rcx
	mov rcx, qword [g_10]
	cmp rax, rcx
	jg b99
	b100:
	mov rcx, qword [rbp - 8]
	mov rax, qword [rbp - 16]
	add rax, qword [r15 + rcx * 8 + 8]
	mov qword [rbp - 16], rax
	b101:
	mov rax, qword [rbp - 8]
	inc rax
	mov qword [rbp - 8], rax
	jmp b92
	b99:
	mov qword [g_0], rbx
	mov qword [g_1], r13
	mov qword [g_2], r12
	mov rax, qword [g_3]
	mov qword [g_3], rax
	call _random
	mov rcx, qword [g_3]
	mov qword [g_3], rcx
	mov r12, qword [g_2]
	mov r13, qword [g_1]
	mov rbx, qword [g_0]
	cdq
	mov rcx, 10
	idiv rcx
	mov rax, rdx
	mov rcx, rax
	add rcx, 1
	mov rax, qword [rbp - 8]
	mov qword [r15 + rax * 8 + 8], rcx
	jmp b98
	b87:
	mov qword [rax + r12 * 8], 0
	dec r12
	jmp b86
__init:
	b102:
	push rbp
	mov rbp, rsp
	mov rcx, 48271
	mov rdx, 2147483647
	mov rsi, 1
	mov qword [g_1], rcx
	mov qword [g_3], rdx
	mov qword [g_4], rsi
	call _main
	mov rsi, qword [g_4]
	mov rdx, qword [g_3]
	mov rcx, qword [g_1]
	leave
	ret 
	section .data
g_10:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_6:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_7:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_5:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_1:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_3:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_2:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_0:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_4:
	db 00H, 00H, 00H, 00H, 00H, 00H, 00H, 00H
g_9:
	dq 1
	db 20H, 00H
g_8:
	dq 0
	db 00H
g_11:
	dq 79
	db 53H, 6FH, 72H, 72H, 79H, 2CH, 20H, 74H, 68H, 65H, 20H, 6EH, 75H, 6DH, 62H, 65H, 72H, 20H, 6EH, 20H, 6DH, 75H, 73H, 74H, 20H, 62H, 65H, 20H, 61H, 20H, 6EH, 75H, 6DH, 62H, 65H, 72H, 20H, 73H, 2EH, 74H, 2EH, 20H, 74H, 68H, 65H, 72H, 65H, 20H, 65H, 78H, 69H, 73H, 74H, 73H, 20H, 69H, 20H, 73H, 61H, 74H, 69H, 73H, 66H, 79H, 69H, 6EH, 67H, 20H, 6EH, 3DH, 31H, 2BH, 32H, 2BH, 2EH, 2EH, 2EH, 2BH, 69H, 00H
g_12:
	dq 12
	db 4CH, 65H, 74H, 27H, 73H, 20H, 73H, 74H, 61H, 72H, 74H, 21H, 00H
g_13:
	dq 5
	db 73H, 74H, 65H, 70H, 20H, 00H
g_14:
	dq 1
	db 3AH, 00H
g_15:
	dq 7
	db 54H, 6FH, 74H, 61H, 6CH, 3AH, 20H, 00H
g_16:
	dq 8
	db 20H, 73H, 74H, 65H, 70H, 28H, 73H, 29H, 00H
