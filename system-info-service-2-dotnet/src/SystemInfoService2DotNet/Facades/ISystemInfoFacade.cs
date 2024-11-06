using SystemInfoService2DotNet.Models;

namespace SystemInfoService2DotNet.Facades;

public interface ISystemInfoFacade
{
    public SystemInfoDTO GetSystemInfo();
    public void Shutdown();
}