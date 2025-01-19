package com.easybit.Accounts.Dto;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountsContactDetailInfo(String message, Map<String,String> contactDetails, List<String> onCallSupport)
{
}
