package com.joshua.summonerswar.global.security.init;

import com.joshua.summonerswar.domain.member.entity.Member;
import com.joshua.summonerswar.domain.member.repository.MemberRepository;
import com.joshua.summonerswar.domain.admin.entity.Resources;
import com.joshua.summonerswar.domain.admin.repository.ResourcesRepository;
import com.joshua.summonerswar.domain.admin.entity.Role;
import com.joshua.summonerswar.domain.admin.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Transactional
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    private final MemberRepository memberRepository;

    private final RoleRepository roleRepository;

    private final ResourcesRepository resourcesRepository;

    private final PasswordEncoder passwordEncoder;

    private static AtomicInteger count = new AtomicInteger(0);

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        setupSecurityResources();

        alreadySetup = true;
    }

    private void setupSecurityResources() {
        Set<Role> roles = new HashSet<>();
        Role adminRole = createRoleIfNotFound("ROLE_ADMIN", "관리자");
        roles.add(adminRole);
        createResourceIfNotFound("/member/**", "", roles, "url");
        createUserIfNotFound("admin@gmail.com", "1111", "관리자계정","1기", roles);
    }

    @Transactional
    public Role createRoleIfNotFound(final String roleName, final String roleDesc) {

        Role role = roleRepository.findByRoleName(roleName);

        if (role == null) {
            role = Role.builder()
                    .roleName(roleName)
                    .roleDesc(roleDesc)
                    .build();
        }

        return roleRepository.save(role);
    }

    @Transactional
    public Resources createResourceIfNotFound(final String resourceName,
                                              final String httpMethod,
                                              final Set<Role> roleSet,
                                              final String resourceType) {
        Resources resources = resourcesRepository.findByResourceNameAndHttpMethod(resourceName, httpMethod);

        if (resources == null) {
            resources = Resources.builder()
                    .resourceName(resourceName)
                    .roleSet(roleSet)
                    .httpMethod(httpMethod)
                    .resourceType(resourceType)
                    .orderNum(count.incrementAndGet())
                    .build();
        }

        return resourcesRepository.save(resources);
    }

    @Transactional
    public Member createUserIfNotFound(final String email, final String password, final String nickname, final String batch, final Set<Role> roleSet) {

        Member member = memberRepository.findByEmail(email)
                .orElse(
                        Member.builder()
                            .email(email)
                            .password(passwordEncoder.encode(password))
                            .nickname(nickname)
                            .batch(batch)
                            .userRoles(roleSet)
                            .build()
                );



        return memberRepository.save(member);
    }

}
