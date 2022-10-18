package com.joshua.summonerswar.domain.admin.service;

import com.joshua.summonerswar.domain.admin.dto.MemberManagerDto;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.repository.RoleRepository;
import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberManagerService {

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public void createUser(final Member member) {

        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        member.setUserRoles(roles);
        memberRepository.save(member);
    }

    public void modifyUser(MemberManagerDto memberManagerDto) {

        ModelMapper modelMapper = new ModelMapper();
        Member member = modelMapper.map(memberManagerDto, Member.class);

        // 유저 정보 권한 수정이 있을 경우
        if (memberManagerDto.getRoles() != null) {
            Set<Role> roles = new HashSet<>();
            memberManagerDto.getRoles().forEach(requestRole -> {
                Role role = roleRepository.findByRoleName(requestRole);
                roles.add(role);
            });
            member.setUserRoles(roles);
        }

        member.setPassword(passwordEncoder.encode(memberManagerDto.getPassword()));
        memberRepository.save(member);

    }

    @Transactional (readOnly = true)
    public MemberManagerDto getUser(Long id) {

        Member member = memberRepository.findById(id).orElse(new Member());
        ModelMapper modelMapper = new ModelMapper();
        MemberManagerDto memberManagerDto = modelMapper.map(member, MemberManagerDto.class);

        List<String> roles = member.getUserRoles()
                .stream()
                .map(Role::getRoleName)
                .collect(Collectors.toList());

        memberManagerDto.setRoles(roles);
        return memberManagerDto;
    }

    @Transactional (readOnly = true)
    public List<MemberManagerDto> getUsers() {
        return memberRepository.findAll()
                .stream().map(MemberManagerDto::toDtoFromEntity)
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        memberRepository.deleteById(id);
    }

}
