using System.Collections.Generic;

namespace SystemInfoService2DotNet.Services;

public interface ISystemInfoService
{
    string GetIpAddress();

    List<string[]> GetRunningProcesses();

    long GetDiskSpace();

    double GetUptime();
}